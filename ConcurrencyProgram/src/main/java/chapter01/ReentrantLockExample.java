package chapter01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hero on 17-3-12.
 */
public class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();
    int t = 5;

    public static void main(String[] args) throws InterruptedException {
        int t = 9;
        assert t == 9;
        System.out.println("hello");
    }

    public void write() {
        lock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            a++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void read() {
        lock.lock();
        try {
            t = a;
        } finally {
            lock.unlock();
        }
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
        TimeUnit.MILLISECONDS.sleep(200);
        t2.start();

        t1.join();
        t2.join();

        System.out.println(t);
    }
}
