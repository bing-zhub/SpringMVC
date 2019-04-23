package dao;

import bean.BookDetails;
import bean.Order;
import bean.OrderDetail;
import cart.ShoppingCart;
import cart.ShoppingCartItem;
import exception.OrderException;
import mapper.BookDetailMapper;
import mapper.OrderDetailsMapper;
import mapper.OrderMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class OrderDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void insertOrderDetail(String bookId, int detailId, int quantity){
        String sql = "insert into orderDetail(detailId, bookId, quantity) VALUES(?,?,?)";
        Object args[] = {detailId, bookId, quantity};
        jdbcTemplate.update(sql, args);
    }

    private void decreaseInventory(String bookID, int quantity) throws OrderException {
        String sql = "select * from books where id = ?";
        BookDetails bd = this.jdbcTemplate.queryForObject(sql, new BookDetailMapper(), bookID);
        int inventory = bd.getInventory();
        Object args[] = {quantity, bookID};
        if((inventory - quantity) >= 0){
            sql = "update books set inventory = inventory - ? where id = ?";
            this.jdbcTemplate.update(sql, args);
        }else{
            throw new OrderException("Not enough of" + bookID + " in stock to complete order.");
        }
    }

    private void buyBook(String bookId, int quantity, int detailId) throws OrderException {
        insertOrderDetail(bookId, detailId, quantity);
        decreaseInventory(bookId, quantity);
    }

    private int getDetailId(){
        int detailId = 0;
        String sql = "select max(detailId) from orderDetail";
        int max =  this.jdbcTemplate.queryForObject(sql, Integer.class);
        detailId = max+1;
        return detailId;
    }

    private void createOrder(String userId, int detailId){
        String sql = "insert into `order`(detailId, userId, createAt) VALUES(?,?,NOW())";
        Object[] args = {detailId,userId};
        this.jdbcTemplate.update(sql, args);
    }

    public void buyBooks(String userId, ShoppingCart cart) throws OrderException {
        Collection items = cart.getItems();
        Iterator i = items.iterator();
        int detailId = getDetailId();
        while (i.hasNext()) {
            ShoppingCartItem sci = (ShoppingCartItem) i.next();
            BookDetails bd = sci.getItem();
            String id = bd.getBookId();
            int quantity = sci.getQuantity();
            buyBook(id, quantity, detailId);
        }
        createOrder(userId, detailId);
    }

    public List<Order> getUserOrders(String userId){
        String sql = "select * from `order` where userid = ? ORDER BY createAt DESC";
        List<Order> orders = this.jdbcTemplate.query(sql, new OrderMapper(), userId);
        for(Order order: orders){
            sql = "SELECT * FROM orderDetail WHERE detailId = ?";
            List<OrderDetail> orderDetails = this.jdbcTemplate.query(sql, new OrderDetailsMapper(), order.getDetailId());
            for(OrderDetail orderDetail : orderDetails){
                sql = "SELECT title from books WHERE id=?";
                String title = this.jdbcTemplate.queryForObject(sql, String.class, orderDetail.getBookId());
                orderDetail.setBookTitle(title);
            }
            order.setDetails(orderDetails);
        }
        return orders;

    }



}
