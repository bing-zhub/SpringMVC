package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String handle(HttpServletRequest req, HttpServletResponse resp){
        return "login";
    }

    @PostMapping("/login")
    public String checkUser(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        String username = "";
        try {
            String iuid = req.getParameter("username");
            String ipwd = req.getParameter("pwd");
            username = userService.checkUser(iuid, ipwd);
            session.setAttribute("username",username);
            session.setAttribute("username",username);
            session.setAttribute("userid",iuid);
            resp.sendRedirect("catalog");
            return null;
        } catch (Exception e) {
            session.setAttribute("errmsg",e.getMessage());
            return "login";
        }
    }
}
