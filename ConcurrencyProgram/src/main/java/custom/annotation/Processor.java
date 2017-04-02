package custom.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by hero on 17-3-27.
 * 处理带标签的类和方法
 */
public class Processor {

    public static void main(String[] args) {
        Class<Decorator> clazz = Decorator.class;
        if (clazz.isAnnotationPresent(NightFire.class)) {
            Annotation annotation = clazz.getAnnotation(NightFire.class);
            NightFire nightFire = (NightFire) annotation;

            System.out.println(nightFire.light());
            for (String star : nightFire.stars()) {
                System.out.printf("%-10s", star);
            }
            System.out.println();
            System.out.println(nightFire.moon());
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Night.class)) {
                Annotation annotation = method.getAnnotation(Night.class);
                Night night = (Night) annotation;
                if (night.enabled()) {
                    try {
                        method.invoke(clazz.newInstance());
                        System.out.println("successed: " + method.getName());
                    } catch (Exception e) {
                        System.out.println("failed: " + method.getName());
                    }
                } else {
                    System.out.println("not process: " + method.getName());
                }
            }
        }
    }
}
