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
 * This is a simple example of an HTTP Servlet. It responds to the GET method of
 * the HTTP protocol.
 */
public class BookDetailsServlet extends HttpServlet {

    private BookDB bookDB;

    public void init() throws ServletException {
        bookDB = (BookDB) getServletContext().getAttribute("bookDB");
        if (bookDB == null)
            throw new UnavailableException("Couldn't get database.");
    }

    public void destroy() {
        bookDB = null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        if (bookId != null) {
            try {
                BookDetails bd = bookDB.getBookDetails(bookId);
                request.setAttribute("bd",bd);
                request.getRequestDispatcher("/WEB-INF/jsp/bookdetail.jsp").forward(request, response);
            } catch (BookNotFoundException ex) {
                throw new ServletException(ex);
            }

        }
    }

    public String getServletInfo() {
        return "The BookDetail servlet returns information about"
                + "any book that is available from the bookstore.";
    }

}
