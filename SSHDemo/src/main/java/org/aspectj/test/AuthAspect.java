package org.aspectj.test;

import com.mchange.v1.util.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by hero on 16-5-21.
 */
@Aspect
public class AuthAspect {

    @Pointcut("execution (* org.aspectj.test.*.*(..)) ")
    public void myPointcut(){}

    @Before(value = "myPointcut()")
    public void beforCut(){
        System.out.println("beforCut");
    }

    @AfterReturning(pointcut = "myPointcut() && args(name, age)", returning = "rev")
    public void authority(String name, int age, Object rev) {
        System.out.println("模拟执行权限检查");
        System.out.println(name + age + rev.toString());
    }

    @AfterThrowing(throwing = "ex", pointcut = "execution(* org.aspectj.test.*.*(..))")
    public void logExption(JoinPoint jp, Throwable ex) {
        System.out.println(ArrayUtils.toString(jp.getArgs()));
        System.out.println("捕获异常" + jp.toString() + ex.toString());
    }
}
