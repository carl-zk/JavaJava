package manual.commit.tx;

import common.DBUtil;
import entity.User;
import repository.UserRepository;

import java.io.PrintWriter;
import java.sql.DriverManager;

/**
 * Created by carl on 5/20/17.
 */
public class TestA {
    public static void main(String[] args) {
        DriverManager.setLogWriter(new PrintWriter(System.out));
        UserRepository userRepository = DBUtil.getIFactory().getUserRepository();
        User user = userRepository.get(1);
        System.out.println("the user is : " + user);
        user.setAge(1);
        int t = userRepository.save(user);
        System.out.println("update user : " + t);
        user = userRepository.get(1);
        System.out.println("the user is : " + user);
    }
}
