package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-16.
 */
public class MarkInThread {
    public static final ThreadLocal<Integer> flag = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public void copyA() {
        flag.set(8);
        System.out.println(flag.get());
    }

    public void copyB() {
        System.out.println(flag.get());
    }

    public static void main(String[] args) {
        MarkInThread test = new MarkInThread();
        test.run();
    }

    public void run() {

        Thread w = new Thread(new Runnable() {
            public void run() {
                copyA();
            }
        }, "copyA");
        Thread r = new Thread(new Runnable() {
            public void run() {
                copyB();
            }
        }, "copyB");

        w.start();
        r.start();
    }
}
