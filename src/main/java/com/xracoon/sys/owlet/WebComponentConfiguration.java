package com.xracoon.sys.owlet;

import ch.qos.logback.classic.selector.servlet.ContextDetachingSCL;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import com.xracoon.sys.owlet.config.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.IntrospectorCleanupListener;

@Configuration
public class WebComponentConfiguration {
    @Bean
    public FilterRegistrationBean authFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean sitemeshFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new SiteMeshFilter());
        registrationBean.addUrlPatterns("*");
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean contextDetachingSCL() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        registrationBean.setListener(new ContextDetachingSCL());
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean introspectorCleanupListener() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        registrationBean.setListener(new IntrospectorCleanupListener());
        return registrationBean;
    }
}
