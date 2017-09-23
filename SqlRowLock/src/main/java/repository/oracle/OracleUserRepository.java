package repository.oracle;

import common.DBUtil;
import entity.User;
import oracle.jdbc.OracleTypes;
import repository.UserRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleUserRepository implements UserRepository {
    public User get(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet res = null;
        Statement stmt;
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            res = stmt.executeQuery("select * from tmp_char");
            if(res.next()){
                System.out.println(res.getString("a").length());
                System.out.println(res.getString("b").length());
            }
            cstmt = conn.prepareCall("{call split_page(?,?,?,?,?)}");
            cstmt.setString(1, "select * from student order by age asc");
            cstmt.setInt(2, 2);
            cstmt.setInt(3, 2);
            cstmt.registerOutParameter(4, OracleTypes.INTEGER);
            cstmt.registerOutParameter(5, OracleTypes.CURSOR);
            cstmt.execute();
            System.out.println(cstmt.getObject(4));
            res = (ResultSet) cstmt.getObject(5);
            while (res.next()) {
                System.out.println(res.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(res, cstmt, conn);
        }
        return null;
    }

    public int save(User user) {
        return 0;
    }

    public static void main(String[] args) {
        OracleUserRepository repository = new OracleUserRepository();
        repository.get(1);
    }
}
