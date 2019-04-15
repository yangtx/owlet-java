/**
* @author yangtianxin
* @date 2016年5月23日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xracoon.sys.owlet.orm.mappers.GenericTypeMapper;
import com.xracoon.sys.owlet.orm.mappers.LinkMapper;
import com.xracoon.sys.owlet.orm.models.GenericType;
import com.xracoon.sys.owlet.orm.models.Link;
import com.xracoon.sys.owlet.vo.LinkVo;

@Controller
@RequestMapping("/link")
public class LinkController {
	@Autowired
	private LinkMapper linkMapper;
	@Autowired
	private GenericTypeMapper typeMapper;
	
	@RequestMapping
	public ModelAndView index(){
		List<Link> links = linkMapper.selectByExample(null);
		List<GenericType> types=typeMapper.selectByExample(null);
		
		ModelAndView mv=new ModelAndView("/link/index");
		mv.addObject("link",links);
		mv.addObject("types", types);
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute("link") LinkVo link, BindingResult result, Model model){
		if(result.hasErrors())
			return "redirect:/link";
			
		Link linkItem=linkMapper.selectByPrimaryKey(link.getId());
		Date now=new Date();
		if(linkItem==null)
		{
			linkItem=new Link();
			linkItem.setCreateTime(now);
		}
		linkItem.setTitle(link.getTitle());
		linkItem.setType(link.getType());
		linkItem.setUrl(link.getUrl());
		linkItem.setSnapshot(link.getSnapshot());
		linkItem.setInfo(link.getInfo());
		linkItem.setModifyTime(now);
		
		int effectLine=linkItem.getId()==null?linkMapper.insert(linkItem):linkMapper.updateByPrimaryKeySelective(linkItem);
	
		return "redirect:/link";
	}
	
	
	@RequestMapping("delete/{id:\\d+}")
	public String delete(@NotNull @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		int effectLine = linkMapper.deleteByPrimaryKey(id);
		redirectAttributes.addAttribute("ts",System.currentTimeMillis());
		return "redirect:/link";
	}
	
}
