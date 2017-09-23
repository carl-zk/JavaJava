import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DoubleIfSingleton {
    private static DoubleIfSingleton ins = null;  //private static volatile DoubleIfSingleton ins = null;

    public static void main(String[] args) throws InterruptedException {
        int max = 64;
        CountDownLatch d = new CountDownLatch(1);
        class R implements Runnable {
            private CountDownLatch d;

            public R(CountDownLatch d) {
                this.d = d;
            }

            public void run() {
                try {
                    d.await();
                    DoubleIfSingleton.getIns();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        Thread[] ts = new Thread[max];
        for (int i = 0; i < max; i++) {
            ts[i] = new Thread(new R(d));
            ts[i].start();
        }

        d.countDown();
        for (int i = 0; i < max; i++)
            ts[i].join();
    }

    public static DoubleIfSingleton getIns() {
        if (null == ins) {
            synchronized (DoubleIfSingleton.class) {
                if (null == ins) {
                    ins = new DoubleIfSingleton();
                }
            }
        }
        return ins;
    }

    private DoubleIfSingleton() {
        System.out.println(this.hashCode());
    }
}
