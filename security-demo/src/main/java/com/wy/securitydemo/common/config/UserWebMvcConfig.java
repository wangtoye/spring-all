package com.wy.securitydemo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangye
 */
@Configuration
public class UserWebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //允许跨域访问的域，可以是一个域的列表，也可以是通配符”*”
                        .allowedOrigins("*")
                        //允许使用的请求方法，以逗号隔开
                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                        //允许自定义的头部，以逗号隔开，大小写不敏感
                        .allowedHeaders("*")
                        //允许脚本访问的返回头，请求成功后，脚本可以在XMLHttpRequest中访问这些头的信息
                        .exposedHeaders("access-control-allow-headers",
                                "access-control-allow-methods",
                                "access-control-allow-origin",
                                "access-control-max-age",
                                "X-Frame-Options")
                        //是否允许请求带有验证信息，XMLHttpRequest请求的withCredentials标志设置为true时，认证通过，浏览器才将数据给脚本程序
                        .allowCredentials(true)
                        //缓存此次请求的秒数。在这个时间范围内，所有同类型的请求都将不再发送预检请求而是直接使用此次返回的头作为判断依据，非常有用，大幅优化请求次数
                        .maxAge(3600);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }
        };
    }
}