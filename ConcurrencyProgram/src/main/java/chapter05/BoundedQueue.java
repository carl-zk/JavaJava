package chapter05;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hero on 17-3-20.
 */
public class BoundedQueue<T> {
    private PriorityQueue<T> que;
    private int capacity = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition empty = lock.newCondition();
    private final Condition full = lock.newCondition();

    public static void main(String[] args) {
        final BoundedQueue<Integer> que = new BoundedQueue<Integer>(5);
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    que.add(i);
                    System.out.println("add=" + i);
                    try {
                        // TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int t = que.peek();
                    System.out.println("peek=" + t);
                }
            }
        });
        t2.setDaemon(true);

        t2.start();
        t1.start();
    }

    BoundedQueue(int capacity) {
        this.capacity = capacity;
        que = new PriorityQueue<T>(capacity);
    }

    public void add(T t) {
        lock.lock();
        try {
            if (capacity == que.size()) {
                System.out.println("----- full -----");
                full.await();
            }
            que.add(t);
            empty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T peek() {
        lock.lock();
        try {
            if (0 == que.size()) {
                System.out.println("---- empty -----");
                empty.await();
            }
            T t = que.peek();
            que.remove(t);
            full.signal();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }
}
