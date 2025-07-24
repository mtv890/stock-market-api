package com.shilton.stockmarketapi.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        LOG.info("[LOG] --> {} {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long totalTime = System.currentTimeMillis() - startTime;

        LOG.info("[LOG] <-- {} {} | Status: {} | Tiempo: {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), totalTime);

        if (ex != null) {
            LOG.error("[LOG] !! Excepción: {}", ex.getMessage());
        }
    }

}
