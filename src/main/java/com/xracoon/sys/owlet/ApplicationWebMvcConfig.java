/*
 Copyright (c) 2008-2019. Fundwit All Rights Reserved.
 */

package com.xracoon.sys.owlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApplicationWebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${app.upload.path}")
    private String uploadPath;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("/assets/**")
                .addResourceLocations("classpath:/assets/")
                .addResourceLocations(uploadPath);
    }
}
