package com.carl.web.support;

import com.carl.web.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author carl
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    public static final String RESPONSE_RESULT_ANNOTATION = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
