package com.carl.web.support.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.carl.web.support.validator.ContainerSize.List;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Repeatable(List.class)
@Constraint(validatedBy = ContainerSizeValidator.class)
public @interface ContainerSize {
    String message() default "{ContainerSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isNullable() default true;

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ContainerSize[] value();
    }
}
