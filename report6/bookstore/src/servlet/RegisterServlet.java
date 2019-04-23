package servlet;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    private UserService userService;

    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        if(userService == null)
            throw new UnavailableException("Couldn't get userService");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        } catch (Exception e) {
            session.setAttribute("errmsg",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
        }
    }

}
