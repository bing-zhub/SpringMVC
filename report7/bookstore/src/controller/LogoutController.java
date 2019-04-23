package controller;

import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LogoutController {

    @RequestMapping
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login");
    }

    @Controller
    public static class RegisterController {

        @Autowired
        private UserService userService;

        @GetMapping("/register")
        public String showRegisterPage(){
            return "register";
        }

        @PostMapping("/register")
        public String registerUser(HttpServletRequest req, HttpServletResponse resp){
            HttpSession session = req.getSession(true);
            try {
                String iusername = req.getParameter("username");
                String ipwd = req.getParameter("pwd");
                String rpwd = req.getParameter("rpwd");
                String iuid = req.getParameter("userid");
                if(iuid.isEmpty())
                    throw new Exception("Username is empty!");
                if(!rpwd.equals(ipwd))
                    throw new Exception("Password does not match!");
                User user = new User();
                user.setPwd(ipwd);
                user.setUserid(iuid);
                user.setUsername(iusername);
                userService.regUser(user);
                PrintWriter out = resp.getWriter();
                out.print("<script> alert('Register successfully! Please login..');" +
                        " setTimeout(function(){location.href='/login'},1000)" +
                        "</script>");
                return null;
            } catch (Exception e) {
                session.setAttribute("errmsg",e.getMessage());
                return "register";
            }
        }

    }
}
