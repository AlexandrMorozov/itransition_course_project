package com.almor.course_project.service.jwt;

import com.almor.course_project.service.entity_services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = parseJwt(request);

        if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {

            String username = jwtUtils.getUserNameFromJwtToken(jwtToken);

            try {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (!userDetails.isEnabled()) {
                    //if user is disabled
                    SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
                } else {
                    UsernamePasswordAuthenticationToken authentication = createJwtToken(userDetails, request);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (UsernameNotFoundException ex) {
                //if user not found or deleted
                SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            }

        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken createJwtToken(UserDetails userDetails,
                                                               HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authentication;
    }

    private String parseJwt(HttpServletRequest request) {
        String requestHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(requestHeader) && requestHeader.startsWith("Bearer ")) {
            return requestHeader.substring(7);
        }

        return null;
    }
}
