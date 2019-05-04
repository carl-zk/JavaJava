package web;

import api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vo.UserVO;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * @author carl
 */
@RestController
public class HelloController implements HelloService {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserVO hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        try {
            return new UserVO(URLDecoder.decode(name, "UTF-8"), age);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody UserVO user) {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
