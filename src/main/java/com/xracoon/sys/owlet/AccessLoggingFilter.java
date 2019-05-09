package com.xracoon.sys.owlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessLoggingFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long begin = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        LOGGER.info("{}\t{}\t{}\t{}", System.currentTimeMillis()-begin, response.getStatus(), request.getMethod(), request.getRequestURI());
    }
}
