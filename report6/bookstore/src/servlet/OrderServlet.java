package servlet;

import bean.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private OrderService orderService;

    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        if (orderService == null)
            throw new UnavailableException("Couldn't get database.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("userid");
        List<Order> orders =  orderService.getUserOrders(userId);
        session.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
    }
}
