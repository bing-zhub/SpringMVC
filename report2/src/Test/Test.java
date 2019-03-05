package Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "Test", urlPatterns = "/test")
public class Test extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println("Test Passed");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        if(error.equals("true")){
            throw new RuntimeException("Server Exploded");
        }

        String browser = req.getHeader("User-Agent");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.println(browser);
    }
}
