package moc.identity;

/**
 * Created by hero on 16-6-1.
 */
public class User {
    private int id;
    private String name;
    private int age;
    private ContectInfo contectInfo;

    protected User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contectInfo=" + contectInfo +
                '}';
    }

    public ContectInfo getContectInfo() {
        return contectInfo;
    }

    public void setContectInfo(ContectInfo contectInfo) {
        this.contectInfo = contectInfo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
