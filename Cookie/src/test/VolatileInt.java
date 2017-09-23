/**
 * Created by hero on 17-8-16.
 */
public class VolatileInt {
    public static void main(String[] args) {
        ThreadLocal<String> o = new ThreadLocal<String>();

        o.set("hello");
        o.get();
    }

}
