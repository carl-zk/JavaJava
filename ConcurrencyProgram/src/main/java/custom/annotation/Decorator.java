package custom.annotation;

import custom.annotation.NightFire.Light;

import java.lang.annotation.Repeatable;

/**
 * Created by hero on 17-3-27.
 * 被标签修饰的类和方法
 */
@NightFire(light = Light.HIGH, stars = {"stara", "starb"}, moon = "round")
public class Decorator {

    @Night(enabled = true)
    void testA() {
        throw new RuntimeException("testA");
    }

    @Night(enabled = false)
    void testB() {
    }

    @Night(enabled = true)
    void testC() {
    }
}
