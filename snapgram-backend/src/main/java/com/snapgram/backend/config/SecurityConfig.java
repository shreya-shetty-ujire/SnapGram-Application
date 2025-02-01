package com.snapgram.backend.config;  // Place the class in an appropriate package


import com.snapgram.backend.SecurityFilter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    // Configure Spring Security to allow all endpoints and disable CSRF
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // AddingJWT filter to vaildate
        return http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login","/register").permitAll()
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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


