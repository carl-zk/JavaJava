package consumer;

import api.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vo.UserVO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author carl
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserVO helloConsumer() {
        try {
            return helloService.hello(URLEncoder.encode("小红", "UTF-8"), 18);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
