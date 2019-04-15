package com.xracoon.sys.owlet.orm.mappersex;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import com.xracoon.sys.owlet.orm.mappers.ArticleMapper;
import com.xracoon.sys.owlet.orm.models.Article;

public interface ArticleMapperEx extends ArticleMapper {
	@Select("SELECT Count(*) FROM article WHERE is_invalid=0 and (status = 1 || uid = #{uid})")
	int countArticles(@Param("uid")long uid);
	@Select("SELECT Count(*) FROM article WHERE is_invalid=0 and (status = 1 || uid = #{uid}) and title like #{kw}")
	int countArticlesKW(@Param("uid")long uid, @Param("kw")String kw);
	
	@Select("SELECT id,type,title,uid,create_time,modify_time,status, is_invalid, abstracts,source,is_top,view_num,comment_num"
			+" FROM article WHERE is_invalid=0 and (status = 1 || uid = #{uid})"
			+" and title like #{kw}"
			+" ORDER BY is_top DESC,create_time DESC limit #{n},#{m}")
	@ResultMap("com.xracoon.sys.owlet.orm.mappers.ArticleMapper.BaseResultMap")
	List<Article> queryArticles(@Param("n")int skipNum, @Param("m")int chooseNum ,@Param("uid") long uid, @Param("kw") String kw);
	
	@Select("SELECT id,type,title,uid,create_time,modify_time,status, is_invalid, abstracts,source,is_top,view_num,comment_num"
			+" FROM article WHERE is_invalid=0 and (status = 1 || uid = #{uid})"
			+" ORDER BY is_top DESC,create_time DESC limit #{n},#{m}")
	@ResultMap("com.xracoon.sys.owlet.orm.mappers.ArticleMapper.BaseResultMap")
	List<Article> selectArticles(@Param("n")int skipNum, @Param("m")int chooseNum ,@Param("uid") long uid);
	
	@Select("SELECT id, title"
			+" FROM article WHERE is_invalid=0 and (status = 1 || uid = #{uid}) "
			+" and create_time < #{ctime}"
			+" ORDER BY create_time DESC limit 1")
	Map<String,Object> queryPrevArticleOfUser(@Param("ctime") Date ctime, @Param("uid") long uid);
	@Select("SELECT id, title"
			+" FROM article WHERE is_invalid=0 and (status = 1 || uid = #{uid}) "
			+" and create_time > #{ctime}"
			+" ORDER BY create_time ASC limit 1")
	Map<String,Object> queryNextArticleOfUser(@Param("ctime") Date ctime, @Param("uid") long uid);
	
	@Select("SELECT id FROM article WHERE uid = #{uid} ORDER BY modify_time DESC limit 1")
	Long selectLastArticleId(@Param("uid") long uid);
	
	@Delete("DELETE FROM article WHERE id= #{id} and uid= #{uid} ")
	int deleteByUidId(@Param("uid") long uid, @Param("id") long id);
	
	@Select("SELECT modify_time FROM article WHERE uid= uid ORDER BY modify_time DESC limit 1")
	Date getLastModifyTimeByUser(@Param("uid")long uid);
	
	@Select("SELECT modify_time FROM article WHERE id= #{id}")
	Date getLastModifyTimeById(@Param("id")long id);
}
