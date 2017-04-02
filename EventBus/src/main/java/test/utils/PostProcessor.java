package test.utils;

import moc.oreh.eventbus.Subscribe;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hero on 17-4-2.
 */
public class PostProcessor {
    private final ConcurrentHashMap<String, Object> container = new ConcurrentHashMap<String, Object>();

    public void init() {
        ClassLoader loader = PostProcessor.class.getClassLoader();
        try {
            Class clazz = loader.loadClass("test.utils.Test");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    method.invoke(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
