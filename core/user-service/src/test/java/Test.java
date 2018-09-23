import com.mysql.cj.MysqlType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @author carl
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mydb?"
                        + "user=root&password=password&useSSL=false");
        int total;
        CallableStatement callableStatement = connect.prepareCall("{call pagination(?,?,?,?)}");
        callableStatement.setString(1, "select * from tbl_user order by id desc");
        callableStatement.setInt(2, 1);
        callableStatement.setInt(3, 6);
        callableStatement.registerOutParameter(4, MysqlType.INT);
        ResultSet resultSet = callableStatement.executeQuery();
        total = callableStatement.getInt(4);
        System.out.println("total = " + total);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("name"));
        }
        connect.close();
    }
}
