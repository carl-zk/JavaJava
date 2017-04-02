package custom.annotation;

import java.lang.annotation.*;

/**
 * Created by hero on 17-3-26.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)  // class level
public @interface NightFire {
    public enum Light {
        LOW, MEDIUM, HIGH
    }

    Light light() default Light.LOW;

    String[] stars() default "";

    String moon() default "big";
}
