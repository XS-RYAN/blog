package com.Ryan.Blog.config;

import com.Ryan.Blog.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/backgroundLoginBHNJM").setViewName("admin/login");
//        registry.addViewController("/login").setViewName("admin/login");
//        registry.addViewController("/admin").setViewName("fuxku");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/fuxku").setViewName("fuxku");
    }

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin","/admin/login");
    }
}
