package user;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class User implements Serializable{
    private static final long serialVersionUID = 61382116205988319L;

    private String name;
    private String passwd;
    private Set<String> roles;

    public User() {
    }

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setRole(String role) {
        if (this.roles == null)
            this.roles = new LinkedHashSet<String>();
        this.roles.add(role);
    }
}
