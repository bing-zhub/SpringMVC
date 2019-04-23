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
import bean.BookDetails;
import exception.BookNotFoundException;
import service.BookService;

public class ShowCartServlet extends HttpServlet {

    private BookService bookService;

    public void init() throws ServletException {
        bookService = (BookService)getServletContext().getAttribute("bookService");
        if (bookService == null)
            throw new UnavailableException("Couldn't get service.");
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
                 BookDetails book = bookService.getBookDetails(bookId);
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
