package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-3-27.
 */
public class DatagramProxy {
    private static MyLog log = new MyLog();

    public static void start(String msg) {
        Plugin.start(msg, new MyLog());
    }

    static class MyLog implements ILog {
        Logger logger = LogManager.getLogger(MyLog.class);

        public void log(String msg) {
            logger.info(msg);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable run = new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    DatagramProxy.start("msg: " + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(10);
    }
}
