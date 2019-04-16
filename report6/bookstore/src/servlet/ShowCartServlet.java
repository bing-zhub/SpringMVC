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

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import database.BookDB;
import database.BookDetails;
import exception.BookNotFoundException;

/**
 * An HTTP servlet that displays the contents of a customer's shopping cart at
 * Duke's Bookstore. It responds to the GET and HEAD methods of the HTTP
 * protocol. This servlet calls other servlets.
 */
public class ShowCartServlet extends HttpServlet {

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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        String bookId = request.getParameter("Remove");
        if (bookId != null) {
            cart.remove(bookId);
        }
        bookId=request.getParameter("Add");
        if(bookId!=null){
        	 try {
                 BookDetails book = bookDB.getBookDetails(bookId);
                 cart.add(bookId, book);
             } catch (BookNotFoundException ex) {
                 throw new ServletException(ex);
             }
        }
        request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp").forward(request, response);


    }

    public String getServletInfo() {
        return "The ShowCart servlet returns information about"
                + "the books that the User is in the process of ordering.";
    }
}
