package com.snapgram.backend.SecurityFilter;

import com.snapgram.backend.config.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    public String extractToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        if(header!=null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request);
            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                String username = jwtUtil.extractUsername(token);

                if (jwtUtil.isTokenExpired(token)) {
                    logger.warn("JWT token expired: " + username);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token Expired");
                    return;  // Don't continue with the filter chain if the token is expired
                }

                // Check if the user is authenticated already
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userdetails = new User(username, "", Collections.emptyList());  // No roles, empty authorities
                    if (jwtUtil.validateToken(token, userdetails)) {
                        // Log the authentication details
                        logger.info("User authenticated: " + username);

                        // Now we create a new Authentication Token and set it into the Security Context
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails,
                                null, userdetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    } else {
                        logger.warn("JWT validation failed for token");
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            // Generic fallback for any other exception
            logger.error("Unexpected error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized access.");
    }
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
