package com.carl.web.support.validator;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * @author carl
 */
public class ContainerSizeValidator implements ConstraintValidator<ContainerSize, Collection> {
    private boolean isNullable;
    private int min;
    private int max;

    @Override
    public void initialize(ContainerSize constraintAnnotation) {
        this.isNullable = constraintAnnotation.isNullable();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext context) {
        Assert.isTrue(0 <= min && min <= max && max <= Integer.MAX_VALUE, "Illegal range size.");
        System.out.println("valid the container size : " + (collection == null ? null : collection.size()));

        return CollectionUtils.isEmpty(collection) ?
                isNullable : min <= collection.size() && collection.size() <= max;
    }
}
