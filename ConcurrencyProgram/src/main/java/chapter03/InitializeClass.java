package chapter03;

/**
 * Created by hero on 17-3-13.
 */
public class InitializeClass {
    public static int a;
    public static Object o;

    public InitializeClass() {
        System.out.println("constructor");
    }

    public static void say() {
    }

    public static void main(String[] args) {
        InitializeClass.say();
        InitializeClass.a = 9;
        InitializeClass.o = new Object();
    }
}
