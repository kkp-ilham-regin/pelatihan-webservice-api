package com.kkp.pelatihanwebservice.internal.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        final String expiredJwt = (String) request.getAttribute("Expired");

        if (expiredJwt != null) {
            logger.error("Unauthorized error {}", expiredJwt);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired, please re-login");
            response.getHeaderNames();
        } else {
            logger.error("Unauthorized error {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Email atau Password tidak sesuai");
        }
    }
}
