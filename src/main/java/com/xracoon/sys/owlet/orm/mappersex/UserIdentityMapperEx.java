package com.xracoon.sys.owlet.orm.mappersex;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.xracoon.sys.owlet.orm.mappers.UserIdentityMapper;

public interface UserIdentityMapperEx extends UserIdentityMapper {
	@Select("SELECT count(*)>0 from user_identity WHERE user = #{user} "
			+ " and auth_channel = #{channel} and channel_key like #{authkey}")
	boolean verifyUser(@Param("user") long user, @Param("channel") int channel, @Param("authkey") String authkey);
	
}
