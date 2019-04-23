package service;

import bean.Order;
import cart.ShoppingCart;
import dao.OrderDao;
import exception.OrderException;

import java.util.List;

public class OrderService {
    private OrderDao orderDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void buyBooks(String userId, ShoppingCart cart) throws OrderException {
        this.orderDao.buyBooks(userId, cart);
    }

    public List<Order> getUserOrders(String userid){
        return this.orderDao.getUserOrders(userid);
    }

}
