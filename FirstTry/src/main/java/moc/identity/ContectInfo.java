package moc.identity;

/**
 * Created by hero on 16-6-1.
 */
public class ContectInfo {
    private String phone;
    private String email;

    public ContectInfo() {
    }

    public ContectInfo(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContectInfo{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
