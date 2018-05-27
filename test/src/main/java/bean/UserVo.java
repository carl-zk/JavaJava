package bean;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String name;
    private int age;
    private List<Skill> skills;
    private Set<Skill> set;
    private Map<Skill, Skill> map;
}