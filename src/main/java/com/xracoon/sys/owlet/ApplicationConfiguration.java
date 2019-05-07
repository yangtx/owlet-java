/*
 Copyright (c) 2008-2019. Fundwit All Rights Reserved.
 */

package com.xracoon.sys.owlet;

import com.xracoon.sys.owlet.utils.IdWorker;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public FilterRegistrationBean accessLoggingFilter(){
        FilterRegistrationBean<AccessLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("*");
        filterRegistrationBean.setFilter(new AccessLoggingFilter());
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);
        return filterRegistrationBean;
    }

    @Bean
    public IdWorker idWorker(){
        // 0:  1bit 符号位，不用
        // timestamp: 41bit, 69年
        // workerId
        //    dataCenterId 5bit
        //    workerId  5bit
        // sequence: 12bit 4095

        return new IdWorker(0, 0, 0);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new DelegatingPasswordEncoder("bcrypt", ImmutableMap.of("bcrypt", new BCryptPasswordEncoder()));
//    }
}
