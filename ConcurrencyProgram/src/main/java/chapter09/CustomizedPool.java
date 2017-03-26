package chapter09;

import java.util.concurrent.*;

/**
 * Created by hero on 17-3-26.
 */
public class CustomizedPool extends ThreadPoolExecutor {

    public static void main(String[] args) {
        CustomizedPool pool = new CustomizedPool(4, 6, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        pool.execute(new Runnable() {
            public void run() {
                boolean r = true;
                while (r) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("hello world");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    r = false;
                }
            }
        });

        pool.shutdown();
    }

    public CustomizedPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CustomizedPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public CustomizedPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public CustomizedPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //startTime.set(System.currentTimeMillis());

        System.out.println("beforeExecute: " + t.getName());
        System.out.println("Thread Pool status: ");
        System.out.println("PoolSize: " + getPoolSize());
        System.out.println("taskCount: " + getTaskCount());
        System.out.println("completedTaskCount: " + getCompletedTaskCount());
        System.out.println("largestPoolSize: " + getLargestPoolSize());
        System.out.println("ActiveCount: " + getActiveCount());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("afterExecute: ");
    }

    @Override
    protected void terminated() {
    }
}
