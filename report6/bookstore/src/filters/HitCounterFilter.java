/*
 * 
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc. Use is
 * subject to license terms.
 *  
 */

package filters;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import util.Counter;

public final class HitCounterFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        if (filterConfig == null)
            return;

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        Counter counter = (Counter) filterConfig.getServletContext()
                .getAttribute("hitCounter");
        writer.println();
        writer
                .println("=======================================================");
        writer.println("The number of hits is: " + counter.incCounter());
        writer
                .println("=======================================================");

        // Log the resulting string
        writer.flush();
        filterConfig.getServletContext().log(sw.getBuffer().toString());

        PrintWriter out = response.getWriter();
        CharResponseWrapper wrapper = new CharResponseWrapper(
                (HttpServletResponse) response);
        chain.doFilter(request, wrapper);
        CharArrayWriter caw = new CharArrayWriter();
        caw.write(wrapper.toString().substring(0,
                wrapper.toString().indexOf("</body>") - 1));
        caw.write("<p>\n<center>You are visitor number&nbsp;<font color='red'>" + counter.getCounter()+ "</font></center>");
        caw.write("\n</body></html>");
        response.setContentLength(caw.toString().length());
        out.write(caw.toString());
        out.close();
    }

    public String toString() {
        if (filterConfig == null)
            return ("HitCounterFilter()");
        StringBuffer sb = new StringBuffer("HitCounterFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
