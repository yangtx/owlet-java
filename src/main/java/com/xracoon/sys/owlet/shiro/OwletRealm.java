package com.xracoon.sys.owlet.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class OwletRealm extends AuthorizingRealm {

	@Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		UserPrincipal principal= (UserPrincipal) principals.getPrimaryPrincipal();
		if(principal==null)
			return super.getAuthorizationCacheKey(principals);
		return principal.getId();
    }
	@Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }
	
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        
       	throw new IncorrectCredentialsException();
    }
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
  
}
