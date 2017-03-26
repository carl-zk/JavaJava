package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-15.
 */
public class InterruptYou {

    public static void main(String[] args) {
        InterruptYou test = new InterruptYou();
        test.run();
    }

    public void run() {
        Thread sleepBeauty = new Thread(new sleepRunner(), "sleepBeauty");
        Thread keepRunning = new Thread(new keepingRunner(), "keepRunning");
        sleepBeauty.start();
        keepRunning.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepBeauty.interrupt();
        keepRunning.interrupt();
        System.out.println("sleep " + sleepBeauty.isInterrupted());
        System.out.println("keep " + keepRunning.isInterrupted());
    }

    private class sleepRunner implements Runnable {

        public void run() {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("wow");
            }
        }
    }

    private class keepingRunner implements Runnable {

        public void run() {
            try {
                while (true) {
                }
            } finally {
                System.out.println("hoops");
            }
        }
    }
}
