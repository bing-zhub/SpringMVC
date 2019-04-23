package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import exception.OrderException;
import service.OrderService;

public class CashierServlet extends HttpServlet {

    private OrderService orderService;

    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        if(orderService == null)
            throw new UnavailableException("Couldn't get OrderService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String userId = (String) session.getAttribute("userid");
        if(userId==null || userId.isEmpty()){
            // Î´µÇÂ¼
            PrintWriter out = resp.getWriter();
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
            PrintWriter out = resp.getWriter();
            out.print("<script> alert('Order created');" +
                    " setTimeout(function(){location.href='/order'},1000)" +
                    "</script>");

        }
    }

    public String getServletInfo() {
        return "The Cashier servlet updates the book database inventory, invalidates the User session, "
                + "thanks the User for the order.";
    }
}
