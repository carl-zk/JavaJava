package chapter01;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-13.
 */
public class EscapeConstructor {
    public static EscapeConstructor obj;
    public final int a;

    public EscapeConstructor() {
        a = 9;
        obj = this;
    }

    public void write() {
        obj = new EscapeConstructor();
    }

    public void read() {
        if (obj != null) {
            System.out.println(a);
        }
    }

    public void run() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                write();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                read();
            }
        });

        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }

    public static void main(String[] args) {
        EscapeConstructor test = new EscapeConstructor();
        test.run();
    }
}
