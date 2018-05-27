import bean.MyBeanUtils;
import bean.Skill;
import bean.User;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Skill> skills = new LinkedList<>();
        skills.add(new Skill("游泳", 10));
        skills.add(new Skill("健身", 9));
        Set<Skill> set = new HashSet<>();
        set.add(new Skill("跑步", 11));
        set.add(new Skill("跑步", 11));
        set.add(new Skill("跑步", 11));
        set.add(new Skill("跑步", 11));
        set.add(new Skill("跑步", 11));
        Map<Skill, Skill> map = new HashMap<>();
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        map.put(new Skill("跑步", 11), new Skill("跑步", 11));
        User user = new User("小红", 18, skills, set, map);
        User vo = new User();

        int n = 1000;

        // use MyBeanUtils.copy
        long now = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            MyBeanUtils.copyFields(user, vo);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - now);

        // use setter directly
        now = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            vo.setSkills(user.getSkills());
            vo.setName(user.getName());
            vo.setAge(user.getAge());
            vo.setMap(user.getMap());
            vo.setSet(user.getSet());
        }
        end = System.currentTimeMillis();
        System.out.println(end - now);
        vo = new User();
        MyBeanUtils.copyFields(user, vo, "map");
        System.out.println(vo);
    }
}
