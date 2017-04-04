package dao.imp;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-4-3.
 */
@Repository
public class UserDaoImp implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        if (user.getId() == null || user.getId().trim().equals("")) {
            user.setId(UUID.randomUUID().toString());
            jdbcTemplate.update("insert into tbl_user VALUES(?, ?, ?)", user.getId(), user.getName(), user.getAge());
        } else
            jdbcTemplate.update("UPDATE tbl_user set name=?, age=? WHERE id=?", user.getName(), user.getAge(), user.getId());
    }

    public User get(String userId) {
        return jdbcTemplate.query("select * from tbl_user where id='" + userId + "'", new ResultSetExtractor<User>() {
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                User user = new User();
                while (resultSet.next()) {
                    user.setId(resultSet.getString(1));
                    user.setName(resultSet.getString(2));
                    user.setAge(resultSet.getInt(3));
                }
                return user;
            }
        });
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
