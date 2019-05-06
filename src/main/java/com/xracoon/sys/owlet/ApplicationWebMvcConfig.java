/*
 Copyright (c) 2008-2019. Fundwit All Rights Reserved.
 */

package com.xracoon.sys.owlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApplicationWebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${app.upload.path}")
    private String uploadPath;  // 最后一个'/'之前的路径作为查找文件时的相对路径
    private static final String FOLDER_SEPARATOR = "/";

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        String normalizedPath = StringUtils.cleanPath(uploadPath);
        if(!normalizedPath.endsWith(FOLDER_SEPARATOR)){
            normalizedPath = normalizedPath+FOLDER_SEPARATOR;
        }

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("/assets/**")
                .addResourceLocations("classpath:/assets/");
        registry.addResourceHandler("/assets/**")
                .addResourceLocations(normalizedPath);
    }
}
