package chapter07;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hero on 17-3-23.
 */
public class UnsafeTest {
    private Integer i = 1;
    private static Unsafe unsafe;
    private static final long valueOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);

            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public void fun() {
        unsafe.compareAndSwapInt(i, valueOffset, 1, 2);
        System.out.println(i);
    }

    public static void main(String[] args) {
        new UnsafeTest().fun();
    }
}
