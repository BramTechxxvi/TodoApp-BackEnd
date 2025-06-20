package org.bram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63343")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "PATCH")
                .allowCredentials(true);
    }
}
