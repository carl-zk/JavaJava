package chapter04;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * PoolB 与PoolA有两个地方不一样
 * 我之前好奇，为什么PoolA会计算future，唤醒或超时醒不都是在占有pool锁么，那既然占有pool锁pool肯定不是空的啊
 * 错了，占有pool锁不代表pool就是不空的。
 * notifyAll会叫醒所有等待线程，然后它们再抢占pool锁，第一个抢到锁的肯定就得到一个Connection了
 * 得到之后就释放锁，让后面的继续竞争。后面的这些就会面临pool又empty了。
 * 我改变了一下
 * 把notifyAll改成notify，在while中不计算remaining而是直接break；
 */
public class PoolB {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public PoolB(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            pool.addLast(ConnectionDriver.createConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                /**
                 * 1.这里不一样
                 */
                pool.notify();
            }
        }
    }

    public Connection fetchConnection(long millis) throws InterruptedException {
        synchronized (pool) {
            while (pool.isEmpty() && millis > 0) {
                pool.wait(millis);
                /**
                 * 2.这里不一样
                 */
                break;
            }
            if (pool.isEmpty()) return null;
            else return pool.removeFirst();
        }
    }
}
