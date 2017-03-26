package chapter04;

/**
 * Created by hero on 17-3-18.
 */
public class SyncTransmit {
    private Object lock = new Object();

    public static void main(String[] args) {
        new SyncTransmit().funA();
    }

    public void test(){

    }

    public void funA() {
        synchronized (lock) {
            System.out.println("A");
            funB();
        }
    }

    public void funB() {
        synchronized (lock) {
            System.out.println("B");
        }
    }
}
