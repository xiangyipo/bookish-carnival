package com.imooc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* 设置resources下的static为静态资源
* */
@ComponentScan
public class ResourcesConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("*.css").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("*.js").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("*.*").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("*.*").addResourceLocations("classpath:/static/fonts/");
    }
}
