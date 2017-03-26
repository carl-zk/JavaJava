package chapter05;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hero on 17-3-19.
 */
public class DIYCache {
    private HashMap<String, String> cache = new HashMap<String, String>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();

    public final String get(String key) {
        rLock.lock();
        try {
            return cache.get(key);
        } finally {
            rLock.unlock();
        }
    }

    public final void put(String key, String value) {
        wLock.lock();
        try {
            cache.put(key, value);
        } finally {
            wLock.unlock();
        }
    }

    public final void clear() {
        wLock.lock();
        try {
            cache.clear();
        } finally {
            wLock.unlock();
        }
    }
}
