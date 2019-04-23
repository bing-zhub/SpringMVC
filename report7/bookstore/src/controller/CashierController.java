package controller;

import cart.ShoppingCart;
import exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CashierController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/cashier")
    public String handle(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(true);
        String userId = (String) session.getAttribute("userid");
        if(userId==null || userId.isEmpty()){
            // Î´µÇÂ¼
            PrintWriter out = null;
            try {
                out = resp.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script> alert('please login and retry');" +
                    " setTimeout(function(){location.href='/login'},1000)" +
                    "</script>");
        }else{
            // ÒÑµÇÂ¼
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }

            try {
                orderService.buyBooks(userId, cart);
            } catch (OrderException e) {
                System.err.println(e.getMessage());
            }
            session.removeAttribute("cart");
            PrintWriter out = null;
            try {
                out = resp.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script> alert('Order created');" +
                    " setTimeout(function(){location.href='/order'},1000)" +
                    "</script>");

        }
        return null;
    }
}
