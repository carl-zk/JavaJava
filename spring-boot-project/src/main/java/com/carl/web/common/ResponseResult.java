package com.carl.web.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author carl
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Documented
public @interface ResponseResult {
}
