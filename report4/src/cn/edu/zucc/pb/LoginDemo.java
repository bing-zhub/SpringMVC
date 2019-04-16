package cn.edu.zucc.pb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "Login",urlPatterns = { "/login"},loadOnStartup=1)
public class LoginDemo extends HttpServlet {
    /**
     * 点击按钮进行登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext applicationContext = null;
        try {
            applicationContext = new ApplicationContextImpl("application.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();

        LoginServiceFactory loginServiceFactory =  (LoginServiceFactory)applicationContext.getBean("LoginSessionFactory");
        ILogin service = loginServiceFactory.createService();

        if(service.login(userid, password)){
            out.println("Login OK.");
        } else {
            out.println("Login FAILED.");
        }
    }

    /**
     * 获取登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");

        String realPath = this.getServletContext().getRealPath("/login.html");
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = response.getOutputStream();

        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}

