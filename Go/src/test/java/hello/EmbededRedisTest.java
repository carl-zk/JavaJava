package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class EmbededRedisTest {
    public static void main(String[] args) throws IOException {
/*        RedisServer server = new RedisServer(6379);
        server.start();
        Jedis jedis = new Jedis();
        jedis.set("c", "c");
        System.out.println(jedis.get("c"));
        jedis.close();
        System.out.println("stop");
        server.stop();*/

        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name"));
    }
}
