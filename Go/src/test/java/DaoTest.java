import moc.oreh.account.entity.User;
import moc.oreh.account.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:META-INF/spring/spring-context.xml")
public class DaoTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional(timeout = 2)
    public void test() throws InterruptedException {
        userRepository.save(new User("小红", 18));
        //TimeUnit.SECONDS.sleep(3);
        User user = userRepository.get(1);
        if (user != null)
            System.out.println(user.getName());
    }
}
