package api;

import vo.UserVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author carl
 */
@FeignClient("hello-service")
public interface HelloService {
    /**
     * hello
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    UserVO hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    /**
     * create user
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    String createUser(@RequestBody UserVO user);
}
