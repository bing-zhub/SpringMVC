package controller;

import bean.BookDetails;
import exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BookDetailsController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/bookdetails")
    public String bookDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String bookId = request.getParameter("bookId");
        if (bookId != null) {
            try {
                BookDetails bd = bookService.getBookDetails(bookId);
                request.setAttribute("bd",bd);
            } catch (BookNotFoundException ex) {
                throw new ServletException(ex);
            }

        }
        return "bookdetail";
    }

}
