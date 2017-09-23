package common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by carl on 5/20/17.
 */
public abstract class DBUtil {
    private static IFactory iFactory = null;

    public static final IFactory getIFactory() {
        return iFactory;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(System.getProperty("db.url"),
                    System.getProperty("db.user"), System.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
        DBUtil.close(resultSet);
        DBUtil.close(statement);
        DBUtil.close(connection);
    }

    static {
        InputStream is = null;
        Properties props = new Properties();
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
            props.load(is);
            System.setProperty("db.url", props.getProperty("db.url"));
            System.setProperty("db.user", props.getProperty("db.user"));
            System.setProperty("db.password", props.getProperty("db.password"));
            if (props.getProperty("db.driver") != null)
                initDriver(props.getProperty("db.driver"));
           // initFactory(props.getProperty("db.factory"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void initDriver(String driver) {
        driver = StringUtil.requireNotBlank(driver).trim();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initFactory(String factory) {
        factory = StringUtil.requireNotBlank(factory).trim();
        try {
            iFactory = (IFactory) Class.forName(factory).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
