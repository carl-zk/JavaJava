package moc.oreh;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author carl
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulServer.class).web(true).run(args);
    }
}
