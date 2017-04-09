package service.myeventbus;

import dao.UserDao;
import entity.User;
import moc.oreh.eventbus.annotation.Subscribe;
import moc.oreh.eventbus.annotation.SubscribeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import service.jdbc.DaoService;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-4-3.
 */
@Component
public class OnlineEventHandler {
    private DaoService daoService;
    private UserDao userDao;

    @Transactional
    @Subscribe(priority = -1)
    public void fun(OnlineEvent e) {
        System.out.println("fun ...");
        userDao.save(new User("jj", 23));
        try {
            int t = 9;
             t /= 0;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Subscribe(mode = SubscribeMode.BACKGROUND, priority = 2)
    @Transactional(timeout = 1)
    public void handle(OnlineEvent event) throws InterruptedException {
        // TimeUnit.SECONDS.sleep(3);  // timeout
        daoService.getUser("1");
        TimeUnit.SECONDS.sleep(3);  // 不会timeout

        System.out.print(TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
        System.out.println(", " + TransactionSynchronizationManager.isSynchronizationActive() + ", " +
                TransactionSynchronizationManager.isActualTransactionActive());

        System.out.print("handle event: " + event.getClass().getName() + " ---> ");
        System.out.println(event.getUser());
    }

    @Subscribe
    public void hand(HelloEvent event){
        System.out.println("hello hello");
    }

    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
