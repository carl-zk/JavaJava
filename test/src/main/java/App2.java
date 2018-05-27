import bean.MyBeanUtils;
import bean.Skill;
import bean.User;
import bean.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class App2 {
    public static void main(String[] args) {
        List<Skill> skills = new LinkedList<>();
        skills.add(new Skill("游泳", 10));
        skills.add(new Skill("健身", 9));
        Set<Skill> set = new HashSet<>();
        set.add(new Skill("跑步", 11));
        set.add(new Skill("跑步", 11));
        Map<Skill, Skill> map = new HashMap<>();
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        User user = new User("小红", 18, skills, set, map);
        UserVo vo = new UserVo();

        int n = 1000000;
        long now, end;


        // use MyBeanUtils
        now = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            MyBeanUtils.copyFields(user, vo);
        }
        end = System.currentTimeMillis();
        System.out.println(end - now);


        // use BeanUtils
        now = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            BeanUtils.copyProperties(user, vo);
        }
        end = System.currentTimeMillis();
        System.out.println(end - now);


        vo = new UserVo();
        MyBeanUtils.copyFields(user, vo, "age");
        System.out.println(vo);
    }
}
