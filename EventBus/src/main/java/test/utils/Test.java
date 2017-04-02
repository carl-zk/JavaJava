package test.utils;

import moc.oreh.eventbus.Subscribe;

/**
 * Created by hero on 17-4-2.
 */
public class Test {
    public final static int a = 1;

    static {
        System.out.println("hello");
    }

    @Subscribe
    public static void print() {
        System.out.println("hello world");
    }
}
