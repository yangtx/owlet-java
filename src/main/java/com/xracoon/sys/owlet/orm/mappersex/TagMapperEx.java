package com.xracoon.sys.owlet.orm.mappersex;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import com.xracoon.sys.owlet.orm.mappers.TagMapper;
import com.xracoon.sys.owlet.orm.models.Tag;

public interface TagMapperEx extends TagMapper {
	//article edit/view query tags
	@Select("SELECT t.* FROM tag t, tag_assign a WHERE t.id = a.tag and a.res_type=0 and a.res_id= #{resid} ORDER BY a.tag_order ASC")
	@ResultMap("com.xracoon.sys.owlet.orm.mappers.TagMapper.BaseResultMap")
	List<Tag> selectByResId(@Param("resid")Long resid);
}
