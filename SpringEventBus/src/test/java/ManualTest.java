import common.Register;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TakePlane;

/**
 * Created by hero on 17-4-2.
 */
public class ManualTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/context.xml");
        TakePlane service = Register.getBean(TakePlane.class);
        service.check();
    }
}
