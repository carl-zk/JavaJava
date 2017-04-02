package chapter09.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Created by hero on 17-3-26.
 * jdbc连接示例
 * Properties使用示例
 */
public class FirstTry {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Properties properties = new Properties();
            init(properties, "db.properties");
            // 1.加载驱动
            Class.forName(properties.getProperty("driver"));
            // 2.建立连接
            conn = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
            // 3.创建statement
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                System.out.println(id + ", " + name);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
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
            input = FirstTry.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(input);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
