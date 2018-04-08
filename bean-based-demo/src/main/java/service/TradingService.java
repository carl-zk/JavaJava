package service;

import config.AppConf;
import dao.UserRepository;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hero
 */
@Service
public class TradingService {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserRepository userRepository;

    public void createByJpa() {
        userRepository.save(new User("小明"));
    }

    public void getUser() {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        //entityManager.
        User user = userRepository.findByName("小红");
        System.out.println(user);
    }

    public void createBySession() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(new User("jack"));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(new Class[]{AppConf.class});

        TradingService tradingService = ctx.getBean(TradingService.class);
        //tradingService.createBySession();
        tradingService.createByJpa();
        tradingService.getUser();
        List<User> list = tradingService.userRepository.listAllUser();
        list.stream().forEach(System.out::println);
    }
}
