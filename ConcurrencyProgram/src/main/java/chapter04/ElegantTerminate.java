package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-15.
 */
public class ElegantTerminate {

    public static void main(String[] args) {
        new ElegantTerminate().run();
    }

    public void run() {
        Runner runner = new Runner();
        Thread thread = new Thread(runner, "control terminate");
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.cancel();
    }

    private class Runner implements Runnable {
        private volatile boolean isOn;

        public Runner() {
            isOn = true;
        }

        public void run() {
            while (isOn) {
                System.out.println("I'm running");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel() {
            isOn = false;
        }
    }
}
