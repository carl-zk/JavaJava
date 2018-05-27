package bean;

import redis.embedded.RedisServer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class MyBeanUtils {

    public static void copyFields(Object from, Object to, String... ignores) {
        if (from == null || to == null) return;
        Field[] fields = from.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> !isIgnore(field.getName(), ignores))
                .forEach(source -> {
                    try {
                        Field target = to.getClass().getDeclaredField(source.getName());
                        if (target != null) {
                            target.setAccessible(true);
                            source.setAccessible(true);
                            target.set(to, source.get(from));
                        }
                    } catch (Exception e) {
                        // ignore all
                    }
                });
    }

    private static boolean isIgnore(String name, String[] ignores) {
        return ignores == null ? false : Arrays.stream(ignores).anyMatch(item -> item.equals(name));
    }

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
      //  RedisServer server = new RedisServer();
        //server.start();
        User user = new User();
        //MyBeanUtils.copyPropertiesByGetterSetter(user, null);
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Arrays.stream(propertyDescriptors).forEach(i -> System.out.println(i.getDisplayName()));
        Optional<PropertyDescriptor> o = Arrays.stream(propertyDescriptors).filter(i->i.getDisplayName().equals("name")).findFirst();
        Method setter = o.get().getWriteMethod();
        setter.setAccessible(true);
        setter.invoke(user, "小红");
        System.out.println(user.getName());
      //  server.stop();
    }
}
