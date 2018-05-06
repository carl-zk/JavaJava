package controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginoutController extends HttpServlet {
    private static final long serialVersionUID = 857223494882602104L;

    private Logger logger = LoggerFactory.getLogger(LoginoutController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("a login request {}", req.getSession().getId());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean rememberMe = Boolean.parseBoolean(req.getParameter("rememberMe"));
        logger.info("name={} passwd={} remember={}", username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        if(subject == null)
            return;
        try {
            subject.login(new UsernamePasswordToken(username, password, false));
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
        }
        req.setAttribute("username", username);
        req.getRequestDispatcher("pages/users/accountInfo.jsp").forward(req, resp);
    }
}
