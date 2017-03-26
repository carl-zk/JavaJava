package chapter04;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by hero on 17-3-16.
 */
public class PoolA {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public PoolA(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            pool.addLast(ConnectionDriver.createConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long millis) throws InterruptedException {
        synchronized (pool) {
            long future = System.currentTimeMillis() + millis;
            long remaining = millis;
            while (pool.isEmpty() && remaining > 0) {
                pool.wait(millis);
                remaining = future - System.currentTimeMillis();
            }
            if (pool.isEmpty()) return null;
            else return pool.removeFirst();
        }
    }
}
