package auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class MySecurityManager extends DefaultWebSecurityManager {
    private static final Logger logger = LoggerFactory.getLogger(MySecurityManager.class);

    @Override
    public AuthenticationInfo authenticate(AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo info = null;
        Collection<Realm> realms = getRealms();
        logger.info("foreach realms");
        for (Realm realm : realms) {
            logger.info("realm name: {}", realm.getName());
        }
        for (Realm realm : realms) {
            info = realm.getAuthenticationInfo(token);
            if (info != null)
                return info;
        }
        return info;
    }
}
