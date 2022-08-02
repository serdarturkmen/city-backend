package com.example.city.security.jwt;

import com.example.city.exception.UserNotActivatedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (authException.getCause() instanceof UserNotActivatedException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request");
    }
}