//package com.snapgram.backend.SecurityFilter;
//
//import com.snapgram.backend.config.JwtUtil;
//import com.snapgram.backend.config.SecurityContext;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//
//@Component
//public class JwtTokenGenerationFilter extends OncePerRequestFilter{
//
//        @Autowired
//        JwtUtil jwtUtil;
//
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//                Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//                if(authentication!=null){
//                    String jwt= jwtUtil.generateToken("");
//                    response.setHeader(SecurityContext.HEADER, jwt );
//                }
//                filterChain.doFilter(request, response);
//            }
//
//        protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//            return !request.getServletPath().equals("/login");
//        }
//
//}
//
//
//
