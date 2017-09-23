import javax.mail.Transport;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WeakRef {
    public static void main(String[] args) throws InterruptedException {

    }

    public static void weakRef() throws InterruptedException {
        User u = new User("小红"), p = u;
        Entity e = new Entity(u);
        u = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(e.get() == null);
        System.out.println(p == null);
    }
}

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }
}

class Entity extends WeakReference<User> {
    public Entity(User referent) {
        super(referent);
    }

    @Override
    public User get() {
        return super.get();
    }
}