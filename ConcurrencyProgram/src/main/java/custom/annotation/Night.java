package custom.annotation;

import java.lang.annotation.*;

/**
 * Created by hero on 17-3-26.
 * 参考http://www.mkyong.com/java/java-custom-annotations-example/
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Night {

    //you can or not
    boolean enabled() default false;
}
