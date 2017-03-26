package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-15.
 */
public class NotifyWait {
    private static Object lock = new Object();
    private boolean flag = true;
    private volatile int count = 1;
    private boolean shutdown = false;

    public void run() throws InterruptedException {
        Thread teacher = new Thread(new Notifier(), "hello");
        Thread student = new Thread(new Waiter(), "world");

        student.start();
        TimeUnit.MILLISECONDS.sleep(300);
        teacher.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new NotifyWait().run();
    }

    private class Notifier implements Runnable {

        public void run() {
            while (!shutdown) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (!flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    flag = false;
                    count--;
                    System.out.println("Notifier");
                    lock.notify();
                }
            }
            synchronized (lock) {
                System.out.println("notify all");
                lock.notifyAll();
            }
        }
    }

    private class Waiter implements Runnable {

        public void run() {
            while (!shutdown) {
                synchronized (lock) {
                    while (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (count <= 0) {
                        shutdown = true;
                    }
                    flag = true;
                    System.out.println("Waiter");
                    lock.notify();
                }
            }
        }

    }
}
