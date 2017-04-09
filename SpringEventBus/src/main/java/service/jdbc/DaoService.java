package service.jdbc;

import common.Register;
import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.myeventbus.OnlineEvent;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-4-3.
 */
@Service
public class DaoService {
    private UserDao userDao;

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User getUser(String userId) {
        return userDao.get(userId);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
