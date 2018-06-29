package com.imooc.intercept;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器注册中心
 */
/*@Configuration*/

public class LoginConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/login");
        loginRegistry.excludePathPatterns("/html/login.html");

        //排除资源
        loginRegistry.excludePathPatterns("/js/*.min.map");
        loginRegistry.excludePathPatterns("/js/*.js");
        loginRegistry.excludePathPatterns("/css/*.css");
        loginRegistry.excludePathPatterns("/images/*.*");
        loginRegistry.excludePathPatterns("/fonts/*.*");


    }
}