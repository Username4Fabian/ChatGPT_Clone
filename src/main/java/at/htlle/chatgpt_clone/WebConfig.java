package at.htlle.chatgpt_clone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // Marks this class as a configuration class
public class WebConfig {

    /*
    * In the context of Spring Framework, a bean is an object managed
    * by the Spring IoC container, created with configuration metadata.
    * In the provided code, the `@Bean` annotation is used to define a `WebMvcConfigurer` object,
    * configured for CORS, which the Spring container instantiates and manages.
    */

    @Bean  // Defines a bean to be managed by Spring
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configures CORS for all paths and allows specific HTTP methods
                // from the specified origin
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedOrigins("http://localhost:8080");
            }
        };
    }
}