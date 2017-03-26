package chapter07;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by hero on 17-3-23.
 * 原子操作
 */
public class UnsafeUtils {
    /**
     * 直接对内存操作的牛逼类，编程时不推荐使用
     */
    private static Unsafe unsafe;

    /**
     * @param obj
     * @param fieldName
     * @param expect    int  long  Object
     * @param update    int  long  Object 这3个参数一一对应
     * @return
     */
    public static boolean compareAndSet(Object obj, String fieldName, Object expect, Object update) {
        try {
            init();
            long valueOffset = computeOffset(obj, fieldName);

            if (expect instanceof Integer)
                unsafe.compareAndSwapInt(obj, valueOffset, (Integer) expect, (Integer) update);
            else if (expect instanceof Long)
                unsafe.compareAndSwapLong(obj, valueOffset, (Long) expect, (Long) update);
            else
                unsafe.compareAndSwapObject(obj, valueOffset, expect, update);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    private static void init() throws Exception {
        if (unsafe == null) {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        }
    }

    private static long computeOffset(Object obj, String fieldName) throws NoSuchFieldException {
        return unsafe.objectFieldOffset(obj.getClass().getDeclaredField(fieldName));
    }

    public static void main(String[] args) {
        Email email = new Email("zxf@123.com");
        User user = new User("carl", 999, email);

        Email email1 = new Email("hello.456");

        System.out.println(user);
        UnsafeUtils.compareAndSet(user, "name", "carl", "hhh");
        UnsafeUtils.compareAndSet(user, "age", 999, 99);
        UnsafeUtils.compareAndSet(user, "email", email, email1);
        System.out.println(user);
    }
}
