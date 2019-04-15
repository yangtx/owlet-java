package com.xracoon.sys.owlet.orm.mappersex;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import com.xracoon.sys.owlet.orm.mappers.TagAssignMapper;
import com.xracoon.sys.owlet.orm.models.TagAssign;
import com.xracoon.sys.owlet.orm.modelsex.TagEx;


public interface TagAssignMapperEx extends TagAssignMapper {
	//article index 页批量查询
	@Select("SELECT * FROM tag_assign WHERE res_type=0 and res_id in (${ids}) ORDER BY tag_order ASC")
	@ResultMap("com.xracoon.sys.owlet.orm.mappers.TagAssignMapper.BaseResultMap")
	List<TagAssign> selectByResIds(@Param("ids")String ids);
	
	@Delete("DELETE FROM tag_assign WHERE res_type=0 and res_id = #{resid}")
	int deleteByResId(@Param("resid") long resId);
	
	//article存储时查询
	@Select("SELECT * FROM tag_assign WHERE res_type=0 and res_id= #{resid} ORDER BY tag_order ASC")
	@ResultMap("com.xracoon.sys.owlet.orm.mappers.TagAssignMapper.BaseResultMap")
	List<TagAssign> selectByResId(@Param("resid")Long resid);
	
	//数量查询统计
	@Select("SELECT t.id as id, tname, img, count(*) as count FROM tag_assign ta LEFT JOIN  tag  t ON t.id=tag "
			+" GROUP BY tag ORDER BY count DESC limit #{num}")
	List<TagEx> assignStatisc(@Param("num") long num);
}
