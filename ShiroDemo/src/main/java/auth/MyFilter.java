package auth;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter extends PathMatchingFilter {
    public MyFilter() {
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Object obj = request.getAttribute("mmp");
        if (obj != null)
            return false;
        return true;
    }
}
