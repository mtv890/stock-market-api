package com.shilton.stockmarketapi.aspect;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AppSecured {

    //If true it will validate

    boolean remoteBuilderCall() default false;
}
