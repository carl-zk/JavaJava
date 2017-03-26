package chapter01;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-13.
 */
public class FinalTest {
    public int a;
    public final int b;
    public static FinalTest obj;

    public FinalTest() {
        this.b = 2;
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = 1;
    }

    public void write() {
        obj = new FinalTest();
    }

    public void read() {
        FinalTest object = obj;
        int tempA = object.a;
        System.out.println("tempA=" + tempA);
        int tempB = object.b;
        System.out.println(tempB);
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

        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

    public static void main(String[] args) {
        FinalTest test = new FinalTest();
        test.run();
    }
}
