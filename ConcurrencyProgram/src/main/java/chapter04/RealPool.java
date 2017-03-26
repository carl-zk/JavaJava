package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-16.
 */
public class RealPool {
    public static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new RealPool().run();
    }

    /**
     * notifyAll 会不会让每个等待线程同时都得到锁
     */
    public void run() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                        //TimeUnit.MILLISECONDS.sleep(500);
                        System.out.println("t1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("t2");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(2);

        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
