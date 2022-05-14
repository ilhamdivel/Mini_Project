package com.example.main.security;

import com.example.main.model.User;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = getToken(request);
        if (jwt != null && jwtProvider.validateToken(jwt)){
            try {
                String userAccount = jwtProvider.getUserAccount(jwt);
                User user = userService.findOne(userAccount);
                //

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
                ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
                list.add(authority);
                UsernamePasswordAuthenticationToken auth
                        = new UsernamePasswordAuthenticationToken(user.getEmail(), null,list);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e ){
                logger.error("Authentication Failed");
            }
        }
        filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.replace("Bearer","");
        }

        return null;
    }
}
