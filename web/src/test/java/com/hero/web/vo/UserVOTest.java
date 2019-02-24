package com.hero.web.vo;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserVOTest {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void idIsNull() {
        UserVO vo = new UserVO(null, "rose", LocalDateTime.now());
        Set<ConstraintViolation<UserVO>> constraintViolations = validator.validate(vo);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void groupTest() {
        UserVO vo = new UserVO(null, "rose", LocalDateTime.now());
        Set<ConstraintViolation<UserVO>> constraintViolations = validator.validate(vo, UserVO.ValidationId.class);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());

        constraintViolations = validator.validate(vo, UserVO.ValidationName.class);
        assertEquals(0, constraintViolations.size());
    }
}
