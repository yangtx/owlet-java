/**
* @author yangtianxin
* @date 2016年5月23日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.xracoon.sys.owlet.component.GlobalConfig;
import com.xracoon.sys.owlet.config.GlobalConst;
import com.xracoon.sys.owlet.config.TokenUser;
import com.xracoon.sys.owlet.orm.mappers.GenericTypeMapper;
import com.xracoon.sys.owlet.orm.mappersex.ArticleMapperEx;
import com.xracoon.sys.owlet.orm.mappersex.TagAssignMapperEx;
import com.xracoon.sys.owlet.orm.mappersex.TagMapperEx;
import com.xracoon.sys.owlet.orm.models.Article;
import com.xracoon.sys.owlet.orm.models.Tag;
import com.xracoon.sys.owlet.orm.models.TagAssign;
import com.xracoon.sys.owlet.orm.models.TagExample;
import com.xracoon.sys.owlet.vo.ArticleSource;
import com.xracoon.sys.owlet.vo.JsonModel;
import com.xracoon.sys.owlet.vo.QueryParam;
import com.xracoon.util.basekit.CollectionsEx;
import com.xracoon.util.basekit.StringEx;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleMapperEx articleMapper;
	@Autowired
	private GenericTypeMapper typeMapper;
	@Autowired
	private TagAssignMapperEx taMapper;
	@Autowired
	private TagMapperEx tagMapper;

	@RequestMapping("/searcher")
	public ModelAndView index1(@NotNull QueryParam param, HttpServletRequest req){
		
		return null;
	}
	
	@RequestMapping
	public ModelAndView index(@NotNull @RequestParam(value="page", defaultValue="1") Integer page, HttpServletRequest req,
			@RequestParam(value="kw", required=false) String kw, @RequestParam(value="tag", defaultValue="-1") long tag){

		TokenUser user=TokenUser.fetchFrom(req);
		long uid= user==null?-1:user.getId();
		
		int count=StringEx.isBlank(kw)?articleMapper.countArticles(uid):articleMapper.countArticlesKW(uid,"%"+kw+"%");
		int pagesize=GlobalConst.PAGESIZE_ARTICLEINDEX;
		int pagecount=(int)Math.ceil(count/(double)pagesize);
		page=Math.min(pagecount, page);
		page=Math.max(1, page);	
		
		List<Article> articles = null;
		if(StringEx.isBlank(kw))
			articles = articleMapper.selectArticles(pagesize*(page-1), pagesize, uid);
		else
			articles = articleMapper.queryArticles(pagesize*(page-1), pagesize, uid, "%"+kw+"%");
		
		ModelAndView mv=new ModelAndView("/article/index");
		mv.addObject("it",articles);
		
		//mv.addObject("count",count);
		//mv.addObject("pagesize",pagesize);
		mv.addObject("pagecount", pagecount);
		mv.addObject("page", page);
		Integer[] pageidxs=listPageIdxs(pagecount,page);
		mv.addObject("pageidxs", pageidxs);
		mv.addObject("types", typeMapper.selectByExample(null));
		mv.addObject("sources", ArticleSource.values());
		mv.addObject("kw", kw);
		mv.addObject("tagRank", taMapper.assignStatisc(20));
		
		List<Long> ids=new ArrayList<>();
		for(Article a: articles)
			ids.add(a.getId());
		
		Map<Long,List<Tag>> m= ids.size()>0?queryTags(ids):null;
		mv.addObject("tagMap", m);
		return mv;
	}
	
	private Integer[] listPageIdxs(int pagecount,int page){
		if(pagecount<=0)
			return new Integer[0];
			
		Set<Integer> pageIdxs=new TreeSet<>();
		pageIdxs.add(1);
		pageIdxs.add(page);
		pageIdxs.add(pagecount);
		int i=0,f=page-1,r=page+1;
		while(i<4){
			if(f>1 && i<4) { pageIdxs.add(f--); i++; }
			if(r<pagecount && i<4) { pageIdxs.add(r++); i++; }
			if(f<=1 && r>=pagecount)
				break;
		}
		
		return pageIdxs.toArray(new Integer[0]);
	}
	
	@RequestMapping(value="{id:\\d+}/edit")
	public ModelAndView edit(@NotNull @PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Article article=articleMapper.selectByPrimaryKey(id);
		TokenUser user=TokenUser.fetchFrom(req);
		
		//其他人无法编辑，只能普通查看
		if(user==null || user.getId()!=article.getUid()){
			resp.sendRedirect(req.getContextPath()+"/article/"+id); 
			return null;
		}
		
		article.setContent("");
		ModelAndView mView=new ModelAndView("article/edit");
		mView.addObject("it", article);
		mView.addObject("last_modify_time", (long)(Math.round(article.getModifyTime().getTime()/1000.0))+"");
		mView.addObject("types", typeMapper.selectByExample(null));
		mView.addObject("sources", ArticleSource.values());
		mView.addObject("tags", tagMapper.selectByResId(article.getId()));
		return mView;
	}

	@RequestMapping(value="{id:\\d+}", method=RequestMethod.GET)
	public ModelAndView view(@NotNull @PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Article article=articleMapper.selectByPrimaryKey(id);
		
		//匿名或别人查看
		TokenUser user=TokenUser.fetchFrom(req);
		if( user==null || user.getId()!=article.getUid()){
			//不能看异常或草稿
			if(article.getIsInvalid() || article.getStatus()==0){
				resp.sendRedirect(req.getContextPath()+"/article"); 
				return null;
			}
			//能查看已发布且正常的，加计数
			else{
				article.setViewNum(article.getViewNum()+1);
				articleMapper.updateByPrimaryKey(article);
			}
		}
		
		article.setContent("");
		ModelAndView mView=new ModelAndView("article/view");
		mView.addObject("it", article);
		mView.addObject("types", typeMapper.selectByExample(null));
		mView.addObject("sources", ArticleSource.values());
		mView.addObject("tags",  tagMapper.selectByResId(article.getId()));
		
		//Map<String,Object> next=articleMapper.queryNextArticleOfUser(article.getCreateTime(), article.getUid());
		//Map<String,Object> prev=articleMapper.queryPrevArticleOfUser(article.getCreateTime(), article.getUid());
		//mView.addObject("next", next);
		//mView.addObject("prev", prev);
		return mView;
	}

	@RequestMapping(value="content/{id:\\d+}", method=RequestMethod.GET)
	public void content(@NotNull @PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Article article=articleMapper.selectByPrimaryKey(id);
		
		//匿名或别人不能看异常或草稿
		TokenUser user=TokenUser.fetchFrom(req);
		if( (user==null || user.getId()!=article.getUid()) && (article.getIsInvalid() || article.getStatus()==0)){
			resp.sendRedirect(req.getContextPath()+"/article"); 
		}
		resp.setContentType("text/plain;charset=UTF-8");
		resp.getWriter().write(article.getContent());
	}
	@RequestMapping(value="content", method=RequestMethod.GET)
	public @ResponseBody String newcontent(){
		return "";
	}
	
	@RequestMapping(value="new", method=RequestMethod.GET)
	public ModelAndView newArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//需要先登录
		TokenUser user=TokenUser.fetchFrom(req);
		if(user==null){
			resp.sendRedirect(req.getContextPath()+"/article"); 
			return null;
		}
		
		ModelAndView mView=new ModelAndView("article/edit");
		Article article=new Article();
		mView.addObject("it", article);
		mView.addObject("types", typeMapper.selectByExample(null));
		mView.addObject("sources", ArticleSource.values());
		return mView;
	}
	
	@RequestMapping(value="delete/{id:\\d+}", method=RequestMethod.GET)
	public String disableArticle(@NotNull @PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//未登录无法操作，返回到列表
		TokenUser user=TokenUser.fetchFrom(req);
		if(user==null){
			resp.sendRedirect(req.getContextPath()+"/article"); 
			return null;
		}
		Article article=articleMapper.selectByPrimaryKey(id);
		//只能修改自己的
		if(article.getUid()==user.getId()){
			article.setIsInvalid(true);
			articleMapper.updateByPrimaryKey(article);
		}
		return "redirect:/article";
	}
	
	@RequestMapping(value="erase/{id:\\d+}", method=RequestMethod.GET)
	public String deleteArticle(@NotNull @PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//未登录无法操作，返回到列表
		TokenUser user=TokenUser.fetchFrom(req);
		if(user==null){
			resp.sendRedirect(req.getContextPath()+"/article"); 
			return null;
		}
		//只能修改自己的
		int n = articleMapper.deleteByUidId(user.getId(), id);
		if(n>0)
			taMapper.deleteByResId(id);
		
		return "redirect:/article";
	}
	
	@RequestMapping(value="cansave")
	public @ResponseBody
	ResponseEntity canSave(HttpServletRequest req, HttpServletResponse resp,
						   @RequestParam("id") Long id, @RequestParam("last_modify_time") Long lastModifyTimeSec) throws JsonGenerationException, JsonMappingException, IOException{

		TokenUser user=TokenUser.fetchFrom(req);
		if(user==null)
			return ResponseEntity.ok(JsonModel.fail(1, "无保存权限"));

		if(id!=null && articleMapper.getLastModifyTimeById(id).getTime()/1000 >lastModifyTimeSec)
			return ResponseEntity.ok(JsonModel.fail(1, "本文已被更新过，请先同步再保存!"));
		
		//10秒
		long gap=GlobalConfig.EDITOR_POST_INTERVAL-(new Date().getTime()/1000 - articleMapper.getLastModifyTimeByUser(user.getId()).getTime()/1000);
		if(gap <=0)
			return ResponseEntity.ok(JsonModel.success(true));
		else
			return ResponseEntity.ok(JsonModel.fail(1, "发布间隔时间太短, 请"+gap+"秒后重试"));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<JsonModel> save(HttpServletRequest req,
			@RequestParam("id") Long id, @NotNull @RequestParam("type") int type,
			@NotNull @RequestParam("status") int status, @NotNull @RequestParam("source") int source,
			@NotNull @RequestParam("title") String title, @NotNull @RequestParam("content") String content,
			@RequestParam("tags") String tags, @RequestParam("last_modify_time") Long lastModifyTimeSec) throws JsonGenerationException, JsonMappingException, IOException{

		TokenUser user=TokenUser.fetchFrom(req);
		//未登录无法操作，返回到列表，其他人无法操作在saveArticle方法中判断
		if(user==null)
			return ResponseEntity.ok(JsonModel.fail(1, "没有保存权限"));
		if(StringEx.isBlank(title))
			return ResponseEntity.ok(JsonModel.fail(1, "标题不能为空"));
		if(StringEx.isBlank(content))
			return ResponseEntity.ok(JsonModel.fail(1, "内容不能为空"));
		if(id!=null && articleMapper.getLastModifyTimeById(id).getTime()/1000 >lastModifyTimeSec)
			return ResponseEntity.ok(JsonModel.fail(1, "本文已被更新过，请先同步再保存!"));
		
		long gap=GlobalConfig.EDITOR_POST_INTERVAL-(new Date().getTime()/1000 - articleMapper.getLastModifyTimeByUser(user.getId()).getTime()/1000);
		if(gap>0)
			return ResponseEntity.ok(JsonModel.fail(1, "发布间隔时间太短, 请"+gap+"秒后重试"));
		
		Date now=new Date();
		Long retId=saveArticle(user.getId(), id, type, status, source, title, content,tags, now);
		
		if(retId!=null){
			Map<String,String> data=new HashMap<>();
			data.put("id", retId+"");
			data.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(now));
			data.put("timestamp", (long)(Math.round(now.getTime()/1000.0))+"");
			return ResponseEntity.ok(JsonModel.success(data));
		}else
			return ResponseEntity.ok(JsonModel.fail(1, "保存失败！"));
	}
	
	private Long saveArticle(long uid, Long id, int type, int status, int source, String title, String content ,String tags, Date now){
		Article article=null;
		if(id!=null){
			article=articleMapper.selectByPrimaryKey(id);
			//无法操作其他人的
			if(article==null || uid!=article.getUid())
				return null;
		}
		
		if(article==null){
			article=new Article();
			article.setUid(uid);
			article.setCreateTime(now);
			article.setIsInvalid(false);
			article.setIsTop(false);
			article.setViewNum(0);
			article.setCommentNum(0);
			article.setIsElite(false);
		}
		article.setType(type);
		article.setStatus(status);
		article.setSource(source);
		article.setTitle(title);
		article.setContent(content);
		article.setModifyTime(now);
		
		int effectLine=article.getId()==null?articleMapper.insert(article):articleMapper.updateByPrimaryKeySelective(article);

		if(article.getId()==null){
			//每个用户一定时间内不能重复提交，所以是有效的
			id= articleMapper.selectLastArticleId(uid);
			article.setId(id);
		}
		
		//已有的TagAssign
		Map<Integer, TagAssign> assignMap=new HashMap<>();
		for(TagAssign t: taMapper.selectByResId(article.getId()))
			assignMap.put(t.getTag(), t);
		
		if(!StringEx.isBlank(tags)){
			//修改后的Tag
			List<String> tagSet=new ArrayList<>(Arrays.asList(tags.toLowerCase().split("\\s*[,;]\\s*")));
			CollectionsEx.distinct(tagSet);

			//已有的Tag
			Map<String, Tag> tagMap=new HashMap<>();
			for(Tag t: tagMapper.selectByExample(null))
				tagMap.put(t.getTname().toLowerCase(), t);
			
			int idx=0;
			for(String ts: tagSet){
				if(!StringEx.isBlank(ts)){
					ts=ts.trim();
					Tag tag=tagMap.get(ts.toLowerCase());
					//save new tag
					if(tag==null){
						tag = new Tag();
						tag.setTname(ts);
						tagMapper.insert(tag);
						
						TagExample te=new TagExample();
						te.createCriteria().andTnameEqualTo(ts);
						Tag qt= tagMapper.selectByExample(te).get(0);
						tag.setId(qt.getId());
					}
					
					//new tag_assign: save
					TagAssign ta=assignMap.get(tag.getId());
					if(ta==null){
						ta=new TagAssign();
						ta.setResType(0);
						ta.setResId(article.getId());
						ta.setTag(tag.getId());
						ta.setTagOrder(idx);
						taMapper.insert(ta);
					}
					//same tag_assign: not change
					else{
						if(ta.getTagOrder()!=idx){
							ta.setTagOrder(idx);
							taMapper.updateByPrimaryKeySelective(ta);
						}
						assignMap.remove(ta.getTag());
					}
					idx++;
				}
			}//for
		}//hasTags
		
		//removed tag_assign
		for(TagAssign ta: assignMap.values())
			taMapper.deleteByPrimaryKey(ta.getId());
		
		return effectLine>0?article.getId():null;
	}
	
	private Map<Long, List<Tag>> queryTags(List<Long> ids){
		Map<Integer, Tag> allTags=new HashMap<>();
		for(Tag t:  tagMapper.selectByExample(null))
			allTags.put(t.getId(), t);
		
		Map<Long, List<Tag>> tagMaps=new HashMap<>();
		int idx=0;
		do{
			StringBuilder idsbatch= new StringBuilder();
			for(int n=0; idx<ids.size() && n<20; idx++,n++)
				idsbatch.append(ids.get(idx)).append(",");
			if(idsbatch.length()>0)
				idsbatch.deleteCharAt(idsbatch.length()-1);
			
			List<TagAssign> assigns = taMapper.selectByResIds(idsbatch.toString());
			for(TagAssign ta: assigns){
				List<Tag> oneResTags= tagMaps.get(ta.getResId());
				if(oneResTags==null){
					oneResTags=new ArrayList<>();
					tagMaps.put(ta.getResId(), oneResTags);
				}
				oneResTags.add(allTags.get(ta.getTag()));
			}
		}while(idx<ids.size());
		
		return tagMaps;
	}
	
	@RequestMapping("/series")
	public String series(){
		return "x/unimplemented";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("kw") String kw){
		return "x/unimplemented";
	}
}
