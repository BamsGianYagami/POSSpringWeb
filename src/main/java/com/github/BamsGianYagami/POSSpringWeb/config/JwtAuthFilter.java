package com.github.BamsGianYagami.POSSpringWeb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.BamsGianYagami.POSSpringWeb.services.JwtService;
import com.github.BamsGianYagami.POSSpringWeb.services.UserInfoService; 
import jakarta.servlet.FilterChain; 
import jakarta.servlet.ServletException; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.context.SecurityContextHolder; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; 
import org.springframework.stereotype.Component; 
import org.springframework.web.filter.OncePerRequestFilter; 
  
import java.io.IOException; 
  
// This class helps us to validate the generated jwt token 
@Component
public class JwtAuthFilter extends OncePerRequestFilter { 

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Autowired
    private JwtService jwtService; 
  
    @Autowired
    // private UserInfoService userDetailsService;
    private UserDetailsService userDetailsService;
  
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
        log.info("do filter internal {}", request.getRequestURL());
        String authHeader = request.getHeader("Authorization"); 
        String token = null; 
        String username = null;

        //use session data
        authHeader = (String) request.getSession().getAttribute("Authorization");
        log.info("Authorization in session data: {}", request.getSession().getAttribute("Authorization"));

        if (authHeader != null && authHeader.startsWith("Bearer ")) { 
            token = authHeader.substring(7); 
            username = jwtService.extractUsername(token); 
        }

        try {
            log.info("securityContext: {}",SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            if (username != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                log.info("masuk username: {}", username); 
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
                if (jwtService.validateToken(token, userDetails)) { 
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.info("#######################username: {} validated!", username);
                } 
            }else {
                log.info("token username {} sedang aktif", username);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("will do filter internal after validation {}", request.getRequestURL());
        filterChain.doFilter(request, response);
    }
} 