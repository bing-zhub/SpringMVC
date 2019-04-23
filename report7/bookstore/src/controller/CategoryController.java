package controller;

import bean.BookDetails;
import cart.ShoppingCart;
import exception.BooksNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class CategoryController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/catalog")
    public String handle(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session =  request.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        String bookId = request.getParameter("bookId");
        if (bookId != null) {

        }

        try {
            Collection<BookDetails> books = bookService.listAllBooks();
            request.setAttribute("books", books);
        } catch (BooksNotFoundException ex) {
            throw new ServletException(ex);
        }

        return "catalog";
    }
}
