package moc.oreh.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hero on 17-8-12.
 */

public class CookieController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("start handle...");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        System.out.println(name + ":" + password);
        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            System.out.println("cookies 列表");
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
            }
        }else{
            System.out.println("cookies == null");
        }
        Cookie cname = new Cookie("name", name);
        cname.setMaxAge(60);
        resp.addCookie(cname);
        Cookie cookie = new Cookie("password", password);
        cookie.setMaxAge(60);
        resp.addCookie(cookie);
        resp.setContentType("text/html;charset=utf-8");
        req.setAttribute("hello", "world");
        resp.getWriter().write("wo shi ni father");
        //req.getRequestDispatcher("/index.jsp").forward(req, resp);
        //getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        //req.getServletContext().getRequestDispatcher("index.jsp").forward(req, resp);
       // super.doPost(req, resp);
    }
}
