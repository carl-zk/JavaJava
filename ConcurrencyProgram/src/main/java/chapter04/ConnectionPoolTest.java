package chapter04;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hero on 17-3-18.
 */
public class ConnectionPoolTest {
    static final int _COUNT = 100;
    static final int _SIZE = 8;
    static final long _WAIT_TIME_IN_MILLIS = 100;
    static PoolA pool = new PoolA(_SIZE);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        end = new CountDownLatch(_COUNT);
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < _COUNT; i++) {
            Thread thread = new Thread(new ConnectionRunner(got, notGot), "ConnectionRunnerThread" + i);
            thread.start();
        }
        long fire = System.currentTimeMillis();
        start.countDown();
        end.await();
        long cost = System.currentTimeMillis() - fire;
        System.out.println("total invoke: " + (_COUNT));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
        System.out.println(cost);
    }

    static class ConnectionRunner implements Runnable {
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(AtomicInteger got, AtomicInteger notGot) {
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {

            try {
                start.await();
                Connection connection = pool.fetchConnection(_WAIT_TIME_IN_MILLIS);
                if (connection != null) {
                    try {
                        connection.createStatement();
                        connection.commit();
                    } finally {
                        pool.releaseConnection(connection);
                        got.incrementAndGet();
                    }
                } else {
                    notGot.incrementAndGet();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            end.countDown();
        }
    }
}
