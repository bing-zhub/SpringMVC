package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class MainFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(filterConfig==null)
            return;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestUrl = httpServletRequest.getRequestURI();
        String remoteAddr = httpServletRequest.getRemoteAddr();
        PrintWriter out = servletResponse.getWriter();
        System.out.println("["+new Date(System.currentTimeMillis()).toString()+"] Get request '"+requestUrl+"' from "+remoteAddr);

        if(requestUrl.equals("/order")){
            String username = (String) servletRequest.getServletContext().getAttribute("username");
            if(username==null  || username.isEmpty()){
                out.println("<script>alert('Login before other operation!'); setTimeout(function(){location.href='/login'},1000);</script> ");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
        out.close();
    }

    @Override
    public void destroy() {

    }
}
