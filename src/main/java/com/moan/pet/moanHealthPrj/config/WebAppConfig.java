package com.moan.pet.moanHealthPrj.config;

import com.moan.pet.moanHealthPrj.app.controller.interceptor.HeaderDaoTypeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Autowired
    HeaderDaoTypeInterceptor headerDaoTypeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerDaoTypeInterceptor);
    }
}
