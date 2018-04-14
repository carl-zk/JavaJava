package common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by hero on 14/04/2018.
 */
@Component
@Aspect
public class ControllerExceptionHandler {
    @Pointcut("execution(** controller.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public void handle(ProceedingJoinPoint point) {
        try {
            point.proceed();
        } catch (Throwable th) {
            // log(th)
            System.out.println("haha, I got you " + th);
        }
    }
}
