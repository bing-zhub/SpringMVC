package mapper;

import bean.OrderDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsMapper implements RowMapper<OrderDetail>{

    @Override
    public OrderDetail mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setRecordId(resultSet.getInt(1));
        orderDetail.setDetailId(resultSet.getInt(2));
        orderDetail.setBookId(resultSet.getString(3));
        orderDetail.setQuantity(resultSet.getInt(4));
        return orderDetail;
    }
}
