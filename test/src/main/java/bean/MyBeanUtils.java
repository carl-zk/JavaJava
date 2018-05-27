package bean;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MyBeanUtils extends BeanUtils {

    static final ConcurrentHashMap<MyKey, List<MyValue>> myCache = new ConcurrentHashMap<>();

    public static void copyWhiteListFields(Object from, Object to, String[] whiteList) {
        Assert.notNull(whiteList, "whiteList is null");
        handle(from, to, whiteList, false);
    }

    public static void copyFields(Object from, Object to, String... ignores) {
        Assert.notNull(from, "from is null");
        Assert.notNull(to, "to is null");
        handle(from, to, ignores, true);
    }

    private static void handle(Object from, Object to, String[] fields, boolean isIgnore) {
        MyKey myKey = new MyKey(from.getClass(), to.getClass(), fields, isIgnore);
        List<MyValue> list = getValueList(myKey);
        list.stream().forEach(x -> {
            try {
                x.setter.invoke(to, x.getter.invoke(from));
            } catch (Exception e) {
            }
        });
    }

    private static List<MyValue> getValueList(MyKey myKey) {
        List<MyValue> list = myCache.get(myKey);
        return list == null ? processKey(myKey) : list;
    }

    // This call is slow so we do it once.
    private static List<MyValue> processKey(MyKey myKey) {
        PropertyDescriptor[] pdsFrom = BeanUtils.getPropertyDescriptors(myKey.from);
        List<MyValue> list = new LinkedList<>();
        Arrays.stream(pdsFrom)
                .filter(pd -> !isIgnore(pd.getName(), myKey.fields, myKey.isIgnore))
                .filter(pd -> !"class".equals(pd.getName()))
                .forEach(pd -> {
                    try {
                        Method getter = pd.getReadMethod();
                        Method setter = BeanUtils.getPropertyDescriptor(myKey.to, pd.getName()).getWriteMethod();
                        setter.setAccessible(true);
                        getter.setAccessible(true);
                        list.add(new MyValue(getter, setter));
                    } catch (Exception e) {
                    }
                });
        myCache.put(myKey, list);
        return list;
    }

    private static boolean isIgnore(String name, String[] fields, boolean isIgnore) {
        if (isIgnore) {
            return fields == null ? false : Arrays.stream(fields).anyMatch(item -> item.equals(name));
        } else {
            return !Arrays.stream(fields).anyMatch(item -> item.equals(name));
        }
    }

    private static class MyKey {
        public Class from;
        public Class to;
        public String[] fields;
        public boolean isIgnore;

        public MyKey(Class from, Class to, String[] fields, boolean isIgnore) {
            this.from = from;
            this.to = to;
            this.fields = fields;
            this.isIgnore = isIgnore;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyKey)) return false;
            MyKey myKey = (MyKey) o;
            return isIgnore == myKey.isIgnore &&
                    Objects.equals(from, myKey.from) &&
                    Objects.equals(to, myKey.to) &&
                    Arrays.equals(fields, myKey.fields);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(from, to, isIgnore);
            result = 31 * result + Arrays.hashCode(fields);
            return result;
        }
    }

    private static class MyValue {
        public Method getter;
        public Method setter;

        public MyValue(Method getter, Method setter) {
            this.getter = getter;
            this.setter = setter;
        }
    }
}
