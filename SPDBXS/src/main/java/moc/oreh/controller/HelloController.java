package moc.oreh.controller;

import moc.oreh.domain.PhisicalPayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by hero on 17-7-15.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/hello")
    @ResponseBody
    public void home(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("param"));
        request.setAttribute("username", "小红");
        LinkedList<PhisicalPayConfig> list = new LinkedList<PhisicalPayConfig>();
        list.add(new PhisicalPayConfig("12123", "jfjdsljf", "fjwjjcv"));
        request.setAttribute("list", list);
        try {
            //response.sendRedirect("/page/result.jsp");
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
