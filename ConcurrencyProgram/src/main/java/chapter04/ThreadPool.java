package chapter04;

/**
 * Created by hero on 17-3-18.
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);
    void shutdown();
    int getJobSize();
}
