package com.smart.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String clientName = (String) args[0];
        System.out.println("how are you" + clientName);
    }
}
