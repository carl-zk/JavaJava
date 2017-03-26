package chapter09.practice.jdbc;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * Created by hero on 17-3-26.
 */
public class FirstTry {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Properties properties = new Properties();
            init(properties, "db.properties");
            // 1.加载驱动
            Class.forName(properties.getProperty("driver"));
            // 建立连接
            conn = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
            // 创建statement
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                System.out.println(id + ", " + name);
            }
            resultSet.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static void init(Properties properties, String fileName) throws Exception {
        InputStream input = null;
        try {
            URL url = FirstTry.class.getClassLoader().getResource(fileName);
            input = new FileInputStream(url.getPath());
            properties.load(input);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
