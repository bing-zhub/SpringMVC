/*
 * 
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc. Use is
 * subject to license terms.
 *  
 */

package listeners;

import javax.servlet.*;

import dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookService;
import service.OrderService;
import service.UserService;
import util.Counter;

public final class ContextListener implements ServletContextListener {
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            BookService bookService = (BookService) ctx.getBean("bookService");
            OrderService orderService = (OrderService) ctx.getBean("orderService");
            UserService userService = (UserService) ctx.getBean("userService");

            context.setAttribute("bookService", bookService);
            context.setAttribute("orderService", orderService);
            context.setAttribute("userService", userService);
        } catch (Exception ex) {
            System.out.println("Couldn't create bookstore database bean: "
                    + ex.getMessage());
        }
        Counter counter = new Counter();
        context.setAttribute("hitCounter", counter);
        counter = new Counter();
        context.setAttribute("orderCounter", counter);
    }


    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();
        context.removeAttribute("bookDB");
        context.removeAttribute("hitCounter");
        context.removeAttribute("orderCounter");
    }
}
