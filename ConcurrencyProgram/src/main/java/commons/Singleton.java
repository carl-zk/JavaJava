package commons;

/**
 * Created by hero on 17-3-27.
 */
public class Singleton {

    public static Singleton getInstance() {
        return Inner.ins;
    }

    public static void print() {
        System.out.println("hello");
    }

    public static void print(String s) {
        Inner.print(s);
    }

    private static class Inner {
        private static final Singleton ins = new Singleton();

        private static void print(String s) {
            System.out.println(s);
        }

        public Inner() {
            System.out.println("inner init");
        }
    }

    private Singleton() {
        System.out.println("init");
    }
}
