package com.smart.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class BeforeAdviceTest {


    public static void main(String[] args) {
        Waiter target = new NaiveWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();
        //1spring提供的代理工厂
        ProxyFactory pf = new ProxyFactory();
        //2设置代理目标
        pf.setTarget(target);
        //3位代理目标类增强
        pf.addAdvice(advice);
        //4生成代理实例
        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("john");
        proxy.serveTo("tom");
    }
}
