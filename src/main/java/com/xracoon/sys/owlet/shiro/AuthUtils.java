package com.xracoon.sys.owlet.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;

public class AuthUtils {
	
	static public UserPrincipal getPrincipal(){
		PrincipalCollection pc=SecurityUtils.getSubject().getPrincipals();
		return (UserPrincipal) (pc!=null?pc.getPrimaryPrincipal():null);
	}
}
