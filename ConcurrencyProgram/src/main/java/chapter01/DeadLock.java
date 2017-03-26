package chapter01;

/**
 * Created by hero on 17-3-11.
 */
public class DeadLock {
    private Object a = new Object();
    private Object b = new Object();

    public static void main(String[] args) {
        new DeadLock().fun();
    }

    private void fun() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (a) {
                    for (int i = 0; i < 100000000; i++) ;
                    synchronized (b) {
                        System.out.println("a b");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (b) {
                    for (int i = 0; i < 100000000; i++) ;
                    synchronized (a) {
                        System.out.println("b a");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
