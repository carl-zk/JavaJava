package org.hibernate.test;

import com.hero.common.Registry;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hero on 16-5-22.
 */
@Transactional
public class UserRepository {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user){
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public User get(int id){
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public UserRepository(){
       // sessionFactory = Registry.getBean("sessionFactory", SessionFactory.class);
    }

}
