/*
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import database.BookDB;
import exception.OrderException;

/**
 * An HTTP servlet that responds to the POST method of the HTTP protocol. The
 * Receipt servlet updates the book database inventory, invalidates the User
 * session, thanks the User for the order.
 */

public class CashierServlet extends HttpServlet {

    private BookDB bookDB;

    public void init() throws ServletException {
        bookDB = (BookDB) getServletContext().getAttribute("bookDB");
        if (bookDB == null)
            throw new UnavailableException("Couldn't get database.");
    }

    public void destroy() {
        bookDB = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String userId = (String) session.getAttribute("userid");
        if(userId==null || userId.isEmpty()){
            // Î´µÇÂ¼
            PrintWriter out = resp.getWriter();
            out.print("<script> alert('please login and retry');" +
                    " setTimeout(function(){location.href='/login'},1000)" +
                    "</script>");
        }else{
            // ÒÑµÇÂ¼
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }

            try {
                bookDB.buyBooks(userId, cart);
            } catch (OrderException e) {
                System.err.println(e.getMessage());
            }
            session.removeAttribute("cart");
            PrintWriter out = resp.getWriter();
            out.print("<script> alert('Order created');" +
                    " setTimeout(function(){location.href='/showcart'},1000)" +
                    "</script>");

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean orderCompleted = true;
        HttpSession session = request.getSession(true);
        ResourceBundle messages = (ResourceBundle) session.getAttribute("messages");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        // Update the inventory
        try {
            bookDB.buyBooks(" ",cart);
        } catch (OrderException e) {
            System.err.println(e.getMessage());
            orderCompleted = false;
        }

        // Payment received -- invalidate the session
        session.invalidate();

        // set content type header before accessing the Writer
        response.setContentType("text/html");
        response.setBufferSize(8192);
        PrintWriter out = response.getWriter();

        // then write the response
        out.println("<html>" + "<head><title>"
                + messages.getString("TitleReceipt") + "</title></head>");

        // Get the dispatcher; it gets the banner to the User
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/banner");

        if (dispatcher != null)
            dispatcher.include(request, response);

        if (orderCompleted)
            out.println("<h3>" + messages.getString("ThankYou")
                    + request.getParameter("cardname") + ".");
        else
            out.println("<h3>" + messages.getString("OrderError"));

        out.println("<p> &nbsp; <p><strong><a href=\""
                + response.encodeURL(request.getContextPath()) + "/enter\">"
                + messages.getString("ContinueShopping")
                + "</a> &nbsp; &nbsp; &nbsp;" + "</body></html>");
        out.close();
    }

    public String getServletInfo() {
        return "The Receipt servlet updates the book database inventory, invalidates the User session, "
                + "thanks the User for the order.";
    }
}
