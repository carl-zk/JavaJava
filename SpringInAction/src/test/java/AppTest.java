import common.config.AppConfig;
import controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hero on 13/04/2018.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("local")
public class AppTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println();
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select count(1) from nh_x_user");
        resultSet.next();
        System.out.println(resultSet.getInt(1));
        System.out.println();
    }

    @Autowired
    UserController userController;

    @Test
    public void testAOP(){
        userController.login();
    }

}
