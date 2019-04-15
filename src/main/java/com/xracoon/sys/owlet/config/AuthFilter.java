package com.xracoon.sys.owlet.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xracoon.sys.owlet.component.GlobalConfig;
import com.xracoon.sys.owlet.utils.CookieUtil;
import com.xracoon.util.basekit.StringEx;
import com.xracoon.util.basekit.security.Crypto;

public class AuthFilter implements Filter {
	private List<String> excludes= Arrays.asList(new String[]{"/user/login","/user/slogin","/user/theme"});
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq=(HttpServletRequest)request;
		
		//pre-process
		String trimedURL=httpReq.getRequestURL().toString().replaceAll("[a-zA-Z0-9]+://[a-zA-Z0-9\\.]+(:\\d+)?", ""); // http://localhost:8001 --> ""
		String contextPath=httpReq.getContextPath();
		String reqPath=trimedURL.substring(contextPath.length());

		//theme
		Cookie cookieTheme=CookieUtil.getCookie(httpReq, GlobalConfig.ATTR_THEME);
		String theme=GlobalConfig.THEME_DEFAULT;
		if(cookieTheme!=null&&GlobalConfig.THEME_NIGHT_MODE.equals(cookieTheme.getValue()))
			theme=GlobalConfig.THEME_NIGHT_MODE;
		else if(cookieTheme!=null&&GlobalConfig.THEME_DAY_MODE.equals(cookieTheme.getValue()))
			theme=GlobalConfig.THEME_DAY_MODE;
		httpReq.setAttribute(GlobalConfig.ATTR_THEME, theme);  //theme
		
		Cookie enthemeCookie=CookieUtil.getCookie(httpReq, GlobalConfig.ATTR_THEME_EDITOR_NIGHT);
		String entheme=enthemeCookie!=null?enthemeCookie.getValue():GlobalConfig.THEME_EDITOR_NIGHT_DEFUALT;
		httpReq.setAttribute(GlobalConfig.ATTR_THEME_EDITOR_NIGHT, entheme);
		
		Cookie edthemeCookie=CookieUtil.getCookie(httpReq, GlobalConfig.ATTR_THEME_EDITOR_DAY);
		String edtheme=edthemeCookie!=null?edthemeCookie.getValue():GlobalConfig.THEME_EDITOR_DAY_DEFAULT;
		httpReq.setAttribute(GlobalConfig.ATTR_THEME_EDITOR_DAY, edtheme);
		
		if(!reqPath.startsWith("/static")){				
			TokenUser user=null;
			try{
				if(!excludes.contains(reqPath)){
					Cookie cookie=CookieUtil.getCookie(httpReq, GlobalConfig.ATTR_AUTHTOKEN);
					byte[] authToken = StringEx.hex2Bytes(cookie.getValue());
					String decryToken= new String(Crypto.decrypt(GlobalConfig.serverkey, authToken));
					ObjectMapper mapper=new ObjectMapper();
					user = mapper.readValue(decryToken, TokenUser.class);
					httpReq.setAttribute(GlobalConfig.ATTR_USER, user);
				}
			}catch(Exception e){
				if(!reqPath.startsWith("/article")){
					//redirect to login
					httpReq.setAttribute(GlobalConfig.ATTR_ERROR, e.getMessage());
					HttpServletResponse httpResp=(HttpServletResponse)response;
					httpResp.sendRedirect(httpReq.getContextPath()+"/user/login");
					return;
				}
			}
		}
		//to next filter and server
		chain.doFilter(request, response); 
		
		//post-process
		//...
	}

	@Override
	public void destroy() {
	}

}
