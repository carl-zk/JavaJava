package soundsystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hero on 10/04/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class PlayerTest {
    @Autowired(required = false)
    private CompactDisc compactDisc;

    @Test
    public void play() {
        Assert.assertNotNull(compactDisc);
        compactDisc.play();
    }
}
