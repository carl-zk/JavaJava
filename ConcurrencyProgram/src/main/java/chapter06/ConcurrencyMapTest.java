package chapter06;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hero on 17-3-21.
 */
public class ConcurrencyMapTest {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();

    @SuppressWarnings("unchecked")
    public void run() {

    }

    public static void main(String[] args) {
        int t = 5, j = 1;
        System.out.println(t >>> 1);
        System.out.println(t >> 1);
        System.out.println(j << 1);
    }
}
