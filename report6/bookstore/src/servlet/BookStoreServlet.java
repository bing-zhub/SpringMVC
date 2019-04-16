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

import database.BookDB;
import database.BookDetails;
import exception.BookNotFoundException;

/**
 * An HTTP Servlet that overrides the service method to return a simple web
 * page.
 */
public class BookStoreServlet extends HttpServlet {

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
        try {
            BookDetails bd = bookDB.getBookDetails("203");
            request.setAttribute("bd", bd);
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        } catch (BookNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    public String getServletInfo() {
        return "The BookStore servlet returns the main web page "
                + "for Duke's Bookstore.";
    }
}
