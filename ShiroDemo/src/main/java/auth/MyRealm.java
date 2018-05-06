package auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import user.User;

public class MyRealm extends AuthorizingRealm {

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权");
        SimpleAuthorizationInfo info = null;
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user.getName().equals("admin")) {
            user.setRole("admin");
            info = new SimpleAuthorizationInfo(user.getRoles());
            info.addStringPermission("admin");
            info.addStringPermission("visit:cowshed");
        }
        if (user.getName().equals("guest")) {
            user.setRole("guest");
            info = new SimpleAuthorizationInfo(user.getRoles());
            info.addStringPermission("visit:cowshed");
        }
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        if (t.getUsername().equals("admin") || t.getUsername().equals("guest")) {
            return new SimpleAuthenticationInfo(new User(t.getUsername(), new String(t.getPassword())), t.getPassword(), getName());
        }
        return null;
    }
}
