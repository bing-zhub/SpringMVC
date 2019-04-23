package mapper;

import bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserid(resultSet.getString(1));
        user.setUsername(resultSet.getString(2));
        user.setPwd(resultSet.getString(3));
        return user;
    }
}
