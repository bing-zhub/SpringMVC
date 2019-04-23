package controller;

import bean.BookDetails;
import cart.ShoppingCart;
import exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ShowCartController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/showcart")
    public String showCart(HttpServletRequest request, HttpServletResponse response){
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

            }
        }
        return "cart";
    }


}
