package com.tshirt.pds.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tshirt.pds.service.CustomUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // if(!request.getRequestURI().contains("/authenticate")){
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            //authorizationHeader.substring(7);
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
                token = authorizationHeader.split(" ")[1];
                username = jwtUtil.extractUsername(token);
            }
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                if(jwtUtil.validateToken(token, userDetails)){
                	 UsernamePasswordAuthenticationToken authentication = jwtUtil.getAuthenticationToken(token, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                	 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        // }
        filterChain.doFilter(request, response);
    }
    
}