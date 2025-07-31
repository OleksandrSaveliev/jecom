package com.tmdna.jecom.security.filters;

import com.tmdna.jecom.common.AuthorizationConstants;
import com.tmdna.jecom.security.authentication.UserAuthentication;
import com.tmdna.jecom.security.exception.TokenAuthenticationException;
import com.tmdna.jecom.security.service.JwtService;
import com.tmdna.jecom.security.user.AuthUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    JwtService jwtService;

    public SecurityAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = request.getHeader(AuthorizationConstants.AUTHORIZATION_HEADER);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = stripBearerPrefix(authorizationHeader);
        AuthUser authUser = jwtService.resolveJwtToken(token);
        UserAuthentication userAuthentication = new UserAuthentication(authUser);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(userAuthentication);
        SecurityContextHolder.setContext(securityContext);
    }

    private String stripBearerPrefix(String authorizationHeader) {
        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new TokenAuthenticationException("Invalid Authorization header format");
        }
        return authorizationHeader.substring(7);
    }
}
