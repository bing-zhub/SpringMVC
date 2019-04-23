package mapper;

import bean.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order>{
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(1));
        order.setDetailId(resultSet.getInt(2));
        order.setCreateAt(resultSet.getDate(4));
        order.setUserId(resultSet.getString(3));
        return order;
    }
}
