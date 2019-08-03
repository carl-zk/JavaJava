package com.hero.web.domain.vo;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserVoTest {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void idIsNull() {
        UserVO vo = new UserVO(null, "", Instant.now());
        Set<ConstraintViolation<UserVO>> constraintViolations = validator.validate(vo);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void groupTest() {
        UserVO vo = new UserVO(null, "rose", Instant.now());
        Set<ConstraintViolation<UserVO>> constraintViolations = validator.validate(vo, UserVO.ValidationId.class);
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());

        constraintViolations = validator.validate(vo, UserVO.ValidationName.class);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void containerSizeTest() {
        InnerVO innerVO = new InnerVO();
        List<String> list = new LinkedList<>();
        list.add("a");
        innerVO.setList(list);
        Set<ConstraintViolation<InnerVO>> constraintViolations = validator.validate(innerVO);
        System.out.println(constraintViolations.size());
        constraintViolations.iterator().forEachRemaining(x -> System.out.println(x.getMessage()));
    }


    class InnerVO {

        /*        @ContainerSize(max = 0, message = "max length is 0")
                @ContainerSize(isNullable = false, message = "must not null")*/
        @Size(max = 0)
        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
}
