package controller;

import bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String handle(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userid");
        List<Order> orders =  orderService.getUserOrders(userId);
        session.setAttribute("orders", orders);
        return "order";
    }

}
