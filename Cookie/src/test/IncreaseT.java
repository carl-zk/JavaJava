import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class IncreaseT {
    public static int t = 0;  //public volatile static int t = 0;

    public static synchronized void add() {
        for (int j = 0; j < 10000; j++)
            t++;
    }

    public static void main(String[] args) throws InterruptedException {
        int max = 128;
        CountDownLatch d = new CountDownLatch(1);
        class R implements Runnable {
            private CountDownLatch d;

            public R(CountDownLatch d) {
                this.d = d;
            }

            public void run() {
                try {
                    d.await();
                    IncreaseT.add();
                    TimeUnit.MILLISECONDS.sleep(500);
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
        System.out.println(IncreaseT.t);
    }
}
