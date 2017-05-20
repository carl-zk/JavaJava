package repository.mysql;

import common.DBUtil;
import entity.User;
import repository.UserRepository;

import java.sql.*;

/**
 * Created by carl on 5/20/17.
 */
public class MySqlUserRepository implements UserRepository {
    public User get(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            stat = conn.prepareStatement("select * from user where id=?");
            stat.setInt(1, id);
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getString(2), resultSet.getInt(3));
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(resultSet, stat, conn);
        }
        return user;
    }

    public int save(User user) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stat = null;
        int res = 0;
        try {
            conn.setAutoCommit(false);
            stat = conn.prepareStatement("update user set name=?, age=? where id=?");
            stat.setString(1, user.getName());
            stat.setInt(2, user.getAge());
            stat.setInt(3, user.getId());
            res = stat.executeUpdate();
            conn.commit();      // 在这里设置断点则其他update命令将等待
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(null, stat, conn);
        }
        return res;
    }
}
