package manual.commit.tx;

import common.DBUtil;
import entity.User;
import repository.UserRepository;

/**
 * Created by carl on 5/20/17.
 */
public class TestB {
    public static void main(String[] args) {
        UserRepository userRepository = DBUtil.getIFactory().getUserRepository();
        User user = userRepository.get(1);
        System.out.println("the user is : " + user);
        user.setAge(10);
        int t = userRepository.save(user);
        System.out.println("update user : " + t);
        user = userRepository.get(1);
        System.out.println("the user is : " + user);
    }
}
