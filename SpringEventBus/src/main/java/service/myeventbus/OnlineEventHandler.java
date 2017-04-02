package service.myeventbus;

import moc.oreh.eventbus.Subscribe;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-4-3.
 */
@Component
public class OnlineEventHandler {
    @Subscribe
    public void handle(OnlineEvent event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.print("handle event: " + event.getClass().getName() + " ---> ");
        System.out.println(event.getUser());
    }
}
