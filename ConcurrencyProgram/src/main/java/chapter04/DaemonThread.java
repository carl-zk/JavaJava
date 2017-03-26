package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-15.
 */
public class DaemonThread {

    public static void run() {
        Thread daemon = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("daemon running...");
                } finally {
                    /**
                     * finally will not be executed
                     */
                    System.out.println("hello world");
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();
    }

    public static void main(String[] args) throws InterruptedException {
        run();
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
