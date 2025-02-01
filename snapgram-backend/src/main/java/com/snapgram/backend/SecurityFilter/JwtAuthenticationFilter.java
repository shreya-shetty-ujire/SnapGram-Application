package com.snapgram.backend.SecurityFilter;

import com.snapgram.backend.config.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    // used for decoding and validating the JWT


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = extractToken(request);
//
//        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            logger.info("Token extracted: " + token);
//            String username = jwtUtil.extractUsername(token);
//
//            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {  //check if the user
//                // is
//                // already
//                // authenticated before
//
//                UserDetails userdetails = new User(username, "", Collections.emptyList());
////                UserDetails userdetails = userDetailsService.loadUserByUsername(username);
////                logger.info("User authorities: " + userDetails.getAuthorities());
//
//                if (jwtUtil.validateToken(token, userdetails)) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails,
//                            null, null);
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                    logger.info("User authenticated: " + username);
//                }else {
//                    logger.warn("Invalid JWT token: " + token);  // Log invalid token
//                }
//            }
//        }
//        filterChain.doFilter(request,response);
//    }
//
    public String extractToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");

        if(header!=null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String username = jwtUtil.extractUsername(token);

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            logger.info("Authenticated User: " + auth.getName());  // Should log the username
        }
        filterChain.doFilter(request, response);
    }
}
