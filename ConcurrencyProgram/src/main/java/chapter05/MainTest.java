package chapter05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hero on 17-3-19.
 */
public class MainTest {
    final TwinsLock lock = new TwinsLock();
    final CountDownLatch gun = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        //new MainTest().run();
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
        } finally {
            lock.unlock();
        }
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runner(), "" + i);
            t.setDaemon(true);
            t.start();
        }
        gun.countDown();
        for (int i = 0; i < 10; i++) {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("------------");

        }
    }

    class Runner implements Runnable {
        public void run() {
            try {
                gun.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
