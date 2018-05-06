import com.hero.common.Registry;
import com.hero.identity.Person;
import org.auto.scan.HelloDto;
import org.auto.scan.HibernateUserRepository;
import org.auto.scan.Skey;
import org.auto.scan.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMain {

    @Before
    public void setup() {
        System.out.println("before");
    }

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
//        Person person = ctx.getBean("person", Person.class);
//        person.useAxe();
        HibernateUserRepository userRepository = ctx.getBean("hibernateUserRepository", HibernateUserRepository.class);
        HibernateUserRepository userRepository1 = (HibernateUserRepository) ctx.getBean("hibernateUserRepository1");
        userRepository.print();
        userRepository1.print();
        System.out.println(userRepository==userRepository1);
//        Person person1 = new Person();
//        person1.setAxe(Registry.getBean(Axe.class));
//        person1.useAxe();
    }

    @After
    public void teardown() {
        System.out.println("after");
    }

    public void testDB() throws SQLException {
        DataSource dataSource = Registry.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO Animal(id, category) VALUES ('4','小狗')");
        statement.close();
        connection.close();
    }
}
