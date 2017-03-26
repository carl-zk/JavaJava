package chapter09.jdbc.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by hero on 17-3-26.
 */
public class ConnectionFactory {
    private static Properties configs = new Properties();

    static {
        InputStream is = null;
        try{
            is = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties");
            configs.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return null;
    }
}
