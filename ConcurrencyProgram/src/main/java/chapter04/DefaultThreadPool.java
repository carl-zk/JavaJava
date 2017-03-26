package chapter04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hero on 17-3-18.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    private volatile boolean running = true;
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    public static int workerNumbers = DEFAULT_WORKER_NUMBERS;
    private AtomicLong workerNumber = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int workerNumbers) {
        this.workerNumbers = workerNumbers > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : workerNumbers < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : workerNumbers;
        initializeWorkers(this.workerNumbers);
    }

    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    public void shutdown() {
        running = false;
        /**
         * 书中没有下面这个notifyAll
         * 因为running在外面，即使设成false但jobs还是empty，程序就不会跳出内循环
         * 同样的，书中的removeWorkers也错了
         * 建议不要再更改pool的大小
         */
        synchronized (jobs) {
            jobs.notifyAll();
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    private void initializeWorkers(int workerNumbers) {
        for (int i = 0; i < workerNumbers; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + workerNumber.incrementAndGet());
            thread.start();
        }
    }

    private class Worker implements Runnable {

        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    /**
                     * 书中是while，那样是shutdown不了线程的，应该是if语句
                     */
                    if (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        job = jobs.removeFirst();
                    }
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }
}