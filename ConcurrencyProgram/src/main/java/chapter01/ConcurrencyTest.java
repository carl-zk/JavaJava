package chapter01;

/**
 * Created by hero on 17-3-9.
 */
public class ConcurrencyTest {
    private final int count = 2000000000;

    public void concurrencyFor() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < count; i++) ;
            }
        });
        thread.start();
        for (int i = 0; i < count; i++) ;
        thread.join();
        long finish = System.currentTimeMillis();
        System.out.print("concurrency time=");
        System.out.println(finish - start);
    }

    public void serialFor() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) ;
        for (int i = 0; i < count; i++) ;
        long finish = System.currentTimeMillis();
        System.out.print("serial time=");
        System.out.println(finish - start);
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrencyTest test = new ConcurrencyTest();
        test.concurrencyFor();
        test.serialFor();
    }
}
