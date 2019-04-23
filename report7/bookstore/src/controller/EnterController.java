package controller;

import bean.BookDetails;
import exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;

@Controller
public class EnterController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/enter")
    public String doGet(Model model) {
        BookDetails bd = null;
        try {
            bd = bookService.getBookDetails("203");
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
        model.addAttribute("bd", bd);
        return "index";
    }
}
