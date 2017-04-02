package service.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-4-2.
 */
@Component
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {
    public void onApplicationEvent(BlackListEvent event) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("警报: 小样,还想发邮件,被我逮到了吧,哈哈: " + event.toString());
    }
}
