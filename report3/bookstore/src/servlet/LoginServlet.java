package servlet;

import database.BookDB;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private BookDB bookDB;

    public void init() throws ServletException {
        bookDB = (BookDB) getServletContext().getAttribute("bookDB");
        if (bookDB == null)
            throw new UnavailableException("Couldn't get database.");
    }

    public void destroy() {
        bookDB.remove();
        bookDB = null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = "";
        try {
            String iuid = req.getParameter("username");
            String ipwd = req.getParameter("pwd");
           username = bookDB.checkUser(iuid, ipwd);
           session.setAttribute("username",username);
            session.setAttribute("userid",iuid);

//            req.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(req, resp);
            resp.sendRedirect("catalog");
        } catch (Exception e) {
            session.setAttribute("errmsg",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }

}
