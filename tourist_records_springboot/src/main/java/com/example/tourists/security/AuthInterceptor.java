package com.example.tourists.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final TokenService tokenService;
    public AuthInterceptor(TokenService tokenService) { this.tokenService = tokenService; }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (path.equals("/") || path.equals("/token") || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs") || request.getMethod().equals("OPTIONS")) return true;

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(401, "Missing Bearer token");
            return false;
        }
        try {
            tokenService.inspect(header.substring(7));
            return true;
        } catch (Exception ex) {
            response.sendError(401, "Invalid or expired token");
            return false;
        }
    }
}
