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
            // δ��¼
            PrintWriter out = resp.getWriter();
            out.print("<script> alert('please login and retry');" +
                    " setTimeout(function(){location.href='/login'},1000)" +
                    "</script>");
        }else{
            // �ѵ�¼
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

    public String getServletInfo() {
        return "The Cashier servlet updates the book database inventory, invalidates the User session, "
                + "thanks the User for the order.";
    }
}
