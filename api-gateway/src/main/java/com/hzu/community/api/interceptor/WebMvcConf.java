package com.hzu.community.api.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {

  @Autowired
  private AuthInterceptor authInterceptor;
  
  @Autowired
  private AuthActionInterceptor authActionInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
    registry
        .addInterceptor(authActionInterceptor)
         .addPathPatterns("/question")
        .addPathPatterns("/publish").addPathPatterns("/profile/questions")
        .addPathPatterns("/profile/replies");

//    super.addInterceptors(registry);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")//拦截所有的url
        .allowedOrigins("*")// 放行哪些原始域，比如"http://domain1.com,https://domain2.com"
        .allowCredentials(true)// 是否发送Cookie信息
        .allowedMethods("GET", "POST", "PUT", "DELETE") // 放行哪些原始域(请求方式)
        .allowedHeaders("*");// 放行哪些原始域(头部信息)
//    super.addCorsMappings(registry);
  }
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {//告知系统当成静态资源访问
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }

}
