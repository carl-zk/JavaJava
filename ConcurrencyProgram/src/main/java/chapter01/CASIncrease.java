package chapter01;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hero on 17-3-11.
 */
public class CASIncrease {
    private static final int _COUNT = 1000;
    private AtomicInteger atomicV = new AtomicInteger(0);
    private int v = 0;

    public static void main(String[] args) {
        new CASIncrease().contrast();
    }

    /**
     * 在并发情况下，对比CAS加法和普通加法
     */
    public void contrast() {
        ArrayList<Thread> list = new ArrayList<Thread>(_COUNT);
        for (int i = 0; i < _COUNT; i++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    commonAdd();
                    casAdd();
                }
            });
            list.add(t);
        }

        for (Thread item : list) {
            item.start();
        }

        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("CAS add " + atomicV.get());
        System.out.println("normal add " + v);
    }

    private void casAdd() {
        for (; ; ) {
            int real = atomicV.get();
            boolean succeed = atomicV.compareAndSet(real, real + 1);
            if (succeed) break;
        }
    }

    private void commonAdd() {
        int real = v;
        v = real + 1;
    }
}
