package com.xracoon.sys.owlet.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class CookieUtil {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(CookieUtil.class);
	public static final String DELIMITER="/%%/";
	
	public static boolean setCookie(HttpServletResponse resp, String domain, 
			String key, String value, int expiry){
		try{
			Cookie cookie=new Cookie(key,value);
			cookie.setPath("/");
			cookie.setDomain(domain);
			cookie.setMaxAge(expiry);
			resp.setHeader("P3P", "CP=\"IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA\"");
			resp.addCookie(cookie);
			return true;
		}catch(Exception e){
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public static Cookie getCookie(HttpServletRequest req, String key){
		try{
			Cookie[] cookies= req.getCookies();
			if(cookies==null||cookies.length==0)
				return null;
			for(Cookie cookie:cookies)
				if(cookie.getName().equalsIgnoreCase(key))
					return cookie;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
}
