package com.xracoon.sys.owlet.orm.mappersex;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import com.xracoon.sys.owlet.orm.mappers.UserMapper;
import com.xracoon.sys.owlet.orm.models.User;

public interface UserMapperEx extends UserMapper {
	@Select("SELECT * from user WHERE username like #{username}")
	@ResultMap("com.xracoon.sys.owlet.orm.mappers.UserMapper.BaseResultMap")
	User getUser(@Param("username") String username);
	
}
