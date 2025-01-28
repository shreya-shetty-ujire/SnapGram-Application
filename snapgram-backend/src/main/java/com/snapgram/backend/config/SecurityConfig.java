package com.snapgram.backend.config;  // Place the class in an appropriate package


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    // Configure Spring Security to allow all endpoints and disable CSRF
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()
        ).csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }

    // Define CORS Configuration globally
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // React's frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify the allowed HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}


