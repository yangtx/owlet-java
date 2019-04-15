/**
* @author yangtianxin
* @date 2016年5月23日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xracoon.sys.owlet.component.GlobalConfig;
import com.xracoon.sys.owlet.config.Codes;
import com.xracoon.sys.owlet.config.TokenUser;
import com.xracoon.sys.owlet.orm.mappersex.UserIdentityMapperEx;
import com.xracoon.sys.owlet.orm.mappersex.UserMapperEx;
import com.xracoon.sys.owlet.orm.models.User;
import com.xracoon.sys.owlet.vo.JsonModel;
import com.xracoon.util.basekit.StringEx;
import com.xracoon.util.basekit.security.Crypto;
import com.xracoon.util.basekit.security.Digest;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired UserMapperEx userMapper;
	@Autowired UserIdentityMapperEx identityMapper;
	private ObjectMapper objMapper= new ObjectMapper();
	
	//hex(sha1(salt+hex(md5(clearpass))+saltRevers))
//	public static void main(String[] args){
//		//327bc4e22b649d47c4546a3ec93f376b
//		String salt="90u0fneflnk123v4f2c4fwefweffu398vfmn38u0938nv(&(f0^$%^fljdjlkfnifhop";
//		String a=salt+StringEx.bytes2Hex(Digest.digest("qwert12345".getBytes(), "md5"))+StringEx.reverse(salt);
//		System.out.println(StringEx.bytes2Hex(Digest.digest(a.getBytes(), "sha1")));
//	}
	
	@RequestMapping(value="login", method={RequestMethod.GET})
	public String login(){
		return "user/login";
	}
	
	@RequestMapping("slogin")
	public String login(HttpServletRequest req, HttpServletResponse resp) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, JsonGenerationException, JsonMappingException, IOException{
		String username=req.getParameter("user");
		String hexMd5Pw=req.getParameter("passwd");
		User user=userMapper.getUser(username);
		if(user!=null){
			String combind = user.getSalt()+hexMd5Pw+StringEx.reverse(user.getSalt());
			String authkey = StringEx.bytes2Hex(Digest.digest(combind.getBytes(), "sha1"));
			
			if(identityMapper.verifyUser(user.getId(), 0, authkey)){
				TokenUser tokenuser=new TokenUser();
				tokenuser.setUsername(user.getUsername());
				tokenuser.setEmail(user.getEmail());
				tokenuser.setId(user.getId());
				
				ObjectMapper mapper=new ObjectMapper();
				String token=StringEx.bytes2Hex((Crypto.encrypt(GlobalConfig.serverkey, mapper.writeValueAsBytes(tokenuser))));
				Cookie cookie=new Cookie(GlobalConfig.ATTR_AUTHTOKEN, token);
				cookie.setPath("/");
				cookie.setMaxAge(Integer.MAX_VALUE);
				resp.addCookie(cookie);
				
//				Cookie cookieTheme=new Cookie(GlobalConfig.ATTR_THEME, user.getTheme());
//				cookieTheme.setPath("/");
//				cookieTheme.setMaxAge(Integer.MAX_VALUE);
//				resp.addCookie(cookieTheme);
			}
		}
		
		//应改为跳转到Refer指定的信息
		return "redirect:/article";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp){
		Cookie cookie=new Cookie(GlobalConfig.ATTR_AUTHTOKEN, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		return "redirect:/article";
	}
	
	@RequestMapping("theme")
	public void setTheme(HttpServletRequest req, HttpServletResponse resp) throws JsonGenerationException, JsonMappingException, IOException{
		String theme = req.getParameter("theme");
		if(GlobalConfig.THEME_DAY_MODE.equals(theme) || GlobalConfig.THEME_NIGHT_MODE.equals(theme)){
			Cookie cookie=new Cookie(GlobalConfig.ATTR_THEME, theme);
			cookie.setPath("/");
			cookie.setMaxAge(Integer.MAX_VALUE);
			resp.addCookie(cookie);
			resp.getWriter().write(objMapper.writeValueAsString(JsonModel.success(theme)));
		}
		else
			resp.getWriter().write(objMapper.writeValueAsString(JsonModel.fail(Codes.PARAMETER_ERROR, "parameter error")));
	}
	
	@RequestMapping("editortheme")
	public void setEditorTheme(HttpServletRequest req, HttpServletResponse resp,
			@NotNull @RequestParam("editortheme") String theme, @NotNull @RequestParam("colormode") String colormode
			) throws JsonGenerationException, JsonMappingException, IOException{
		if(!StringEx.isBlank(theme)){
			String cookieKey=null;
			if(GlobalConfig.THEME_DAY_MODE.equals(colormode))
				cookieKey=GlobalConfig.ATTR_THEME_EDITOR_DAY;
			else if(GlobalConfig.THEME_NIGHT_MODE.equals(colormode))
				cookieKey=GlobalConfig.ATTR_THEME_EDITOR_NIGHT;
			
			if(cookieKey!=null){
				Cookie cookie=new Cookie(cookieKey, theme);
				cookie.setPath("/");
				cookie.setMaxAge(Integer.MAX_VALUE);
				resp.addCookie(cookie);
				resp.getWriter().write(objMapper.writeValueAsString(JsonModel.success(theme)));
			}
			return;
		}
		
		resp.getWriter().write(objMapper.writeValueAsString(JsonModel.fail(Codes.PARAMETER_ERROR, "parameter error")));
	}
}
