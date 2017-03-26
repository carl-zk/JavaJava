package chapter01;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-12.
 */
public class SerialConsistent {
    public int a = 0;
    public volatile boolean b = false;
    public int t = 5;

    public void write() {
        a = 1;
        try {
            Thread.sleep(501);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b = true;
    }

    public void read() {
        if (b) {
            t = a;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SerialConsistent test = new SerialConsistent();
        test.run();
    }

    public void run() throws InterruptedException {
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
        TimeUnit.MILLISECONDS.sleep(500);
        t2.start();

        t1.join();
        t2.join();

        System.out.println(t);
    }
}
