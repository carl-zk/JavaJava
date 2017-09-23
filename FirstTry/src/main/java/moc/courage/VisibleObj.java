package moc.courage;

import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hero on 17-7-12.
 */
public class VisibleObj {

    public static void main(String[] args) throws URISyntaxException {
        Map map = new HashMap();
        String s = (String) map.get("a");
        System.out.println(s==null);
        HttpServletRequest request = new StandardMultipartHttpServletRequest(null);
        s = (String) request.getAttribute("j");
        System.out.println(s==null);
    }
}
