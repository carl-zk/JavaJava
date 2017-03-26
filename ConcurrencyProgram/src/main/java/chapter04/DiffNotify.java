package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-18.
 */
public class DiffNotify {

    public static void main(String[] args) throws InterruptedException {
        new DiffNotify().run();
    }

    public void run() throws InterruptedException {
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait(10 * 1000);
                        System.out.println("t1");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait(10 * 1000);
                        System.out.println("t2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        synchronized (lock) {
            lock.notifyAll();
            //lock.notify();
        }
    }
}
