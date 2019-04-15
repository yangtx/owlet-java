package com.xracoon.sys.owlet.component;

import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class AccessLogFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(AccessLogFilter.class);
    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        long begin = System.currentTimeMillis();
        LOG.info(">> {} {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
        LOG.info(">> {} {}ms {} {}", httpServletResponse.getStatus(), System.currentTimeMillis()-begin, httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
    }
}
