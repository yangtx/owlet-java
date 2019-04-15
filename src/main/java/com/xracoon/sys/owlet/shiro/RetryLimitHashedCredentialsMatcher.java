/**
 * Created by XRacoon on 2017/5/1.
 */

package com.xracoon.sys.owlet.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import java.util.concurrent.atomic.AtomicInteger;


public class RetryLimitHashedCredentialsMatcher extends
        HashedCredentialsMatcher {
    private int retryLimit=5;
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount); //add retry count
        }
        if (retryCount.incrementAndGet() > retryLimit) //count++ and check
            throw new ExcessiveAttemptsException();

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches)
            passwordRetryCache.remove(username);  // clean retry count
        return matches;
    }

    public int getRetryLimit() {
        return retryLimit;
    }
    public void setRetryLimit(int retryLimit) {
        this.retryLimit = retryLimit;
    }
}