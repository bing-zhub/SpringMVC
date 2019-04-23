package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import bean.BookDetails;
import exception.BooksNotFoundException;
import service.BookService;

public class CatalogServlet extends HttpServlet {
    private BookService bookService;

    public void init() throws ServletException {
        bookService = (BookService) getServletContext().getAttribute("bookService");
        if(bookService == null){
            throw new UnavailableException("Couldn't get service.");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        String bookId = request.getParameter("bookId");
        if (bookId != null) {
           
        }

        request.setAttribute("msg","bing");

        try {
            Collection<BookDetails> books = bookService.listAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(request, response);
        } catch (BooksNotFoundException ex) {
            throw new ServletException(ex);
        }
    }


    public String getServletInfo() {
        return "The Catalog servlet adds books to the User's "
                + "shopping cart and prints the catalog.";
    }
}
