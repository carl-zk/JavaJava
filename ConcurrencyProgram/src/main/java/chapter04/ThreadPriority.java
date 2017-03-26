package chapter04;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-14.
 */
public class ThreadPriority {
    public static final int _SIZE = 10;
    private volatile boolean isEnd;
    private CyclicBarrier cyclicBarrier;

    public ThreadPriority() {
        cyclicBarrier = new CyclicBarrier(_SIZE);
        isEnd = false;
    }

    public static void main(String[] args) {
        ThreadPriority threadPriority = new ThreadPriority();
        threadPriority.run();
    }

    public void run() {
        Job[] jobs = new Job[_SIZE];
        for (int i = 0; i < _SIZE; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            jobs[i] = new Job(priority);
            Thread thread = new Thread(jobs[i]);
            thread.setPriority(priority);
            thread.start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isEnd = true;

        for (Job job : jobs) {
            System.out.printf("%5d %15d\n", job.priority, job.count);
        }
    }

    private class Job implements Runnable {
        private int priority;
        private int count;

        public Job(int priority) {
            this.priority = priority;
            this.count = 0;
        }

        public void run() {
            try {
                cyclicBarrier.await();
                while (!isEnd) {
                    this.count++;
                    Thread.yield();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
