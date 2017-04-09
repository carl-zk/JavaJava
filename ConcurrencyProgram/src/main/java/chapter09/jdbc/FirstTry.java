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
            PreparedStatement selectAll = conn.prepareStatement("SELECT * FROM User");
            resultSet = selectAll.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                System.out.println(id + ", " + name);
            }
            selectAll.close();
            selectAll = null;
        } catch (Exception ex) {


            ex.printStackTrace();
        } finally {
            /**
             * 关闭是递进的,高级的关闭同时会关闭低级的
             *
             * 可参考这段代码来关闭
             * catch (SQLException ex) {
             // Release Connection early, to avoid potential connection pool deadlock
             // in the case when the exception translator hasn't been initialized yet.
             JdbcUtils.closeStatement(stmt);
             stmt = null;
             DataSourceUtils.releaseConnection(con, getDataSource());
             con = null;
             throw getExceptionTranslator().translate("StatementCallback", getSql(action), ex);
             }
             finally {
             JdbcUtils.closeStatement(stmt);
             DataSourceUtils.releaseConnection(con, getDataSource());
             }
             */
            if (resultSet.isClosed()) {
                System.out.println("res null");
            } else {
                resultSet.close();
                System.out.println("can res c");
            }
            if (conn != null) {
                conn.close();
            }
            if (stmt.isClosed()) {
                System.out.println("stmt closed");
            } else {
                stmt.close();
            }
            conn = null;    //help GC
            stmt = null;
            resultSet = null;
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
