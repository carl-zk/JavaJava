package com.dao.imp;

import com.dao.UserRepository;
import com.entity.User;
import moc.hibernate.BaseRepository;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-2-27.
 */

public class HibernateUserRepository extends BaseRepository implements UserRepository {
    @Transactional
    public User getUser(String name) {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//        }
        Object object = getCurrentSession().get(User.class.getName(), name);
        if (object == null) {
            throw new RuntimeException("user 不存在");
        }
        return (User) object;
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 2)
    public void save(User user) {
        getCurrentSession().saveOrUpdate(user);
    }

//    这样是没有事务的，@Transactional是不起作用的
    @Transactional(timeout = 1)
    public void test() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hhhh");
    }
}
