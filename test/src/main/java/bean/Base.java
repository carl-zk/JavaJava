package bean;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Base implements Serializable {
    private String name;
    private int age;
    private List<Skill> skills;
}
