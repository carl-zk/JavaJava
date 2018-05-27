package bean;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base implements Serializable {
    private Set<Skill> set;
    private Map<Skill, Skill> map;

    public User(String name, int age, List<Skill> skills, Set<Skill> set, Map<Skill, Skill> map) {
        super(name, age, skills);
        this.set = set;
        this.map = map;
    }

    @Override
    public String toString() {
        return "User{" +
                "set=" + set +
                ", map=" + map +
                "} " + super.toString();
    }
}
