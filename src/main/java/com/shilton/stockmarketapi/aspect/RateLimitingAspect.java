package com.shilton.stockmarketapi.aspect;

import com.shilton.stockmarketapi.exception.TooManyRequestsException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Aspect
@Component
public class RateLimitingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(RateLimitingAspect.class);
    private final Map<String, Deque<Long>> requestTimestamps = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimited)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        int limit = rateLimited.maxRequests();
        int window = rateLimited.timeWindowInSeconds();

        Deque<Long> timestamps = requestTimestamps.get(userId);
        if (timestamps == null) {
            timestamps = new ArrayDeque<>();
            requestTimestamps.put(userId, timestamps);
        }


        synchronized (timestamps) {
            long now = System.currentTimeMillis();
            while (!timestamps.isEmpty() && now - timestamps.peekFirst() > window * 1000) {
                timestamps.pollFirst();
            }
            if (timestamps.size() >= limit) {
                LOG.error("User {} exceeded rate limit of {} requests in {} seconds", userId, limit, window);
                throw new TooManyRequestsException("Rate limit exceeded. Try again later.");
            }
            timestamps.addLast(now);
        }

        return joinPoint.proceed();
    }
}
