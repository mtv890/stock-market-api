package com.shilton.stockmarketapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class SecurityInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Pointcut("@annotation(AppSecured)")
    public void hi() {
        //Annotation pointcut
    }

    @Before("hi()")
    public void executeSecurityCheck(JoinPoint jp){
        LOG.info("hi");
    }
}
