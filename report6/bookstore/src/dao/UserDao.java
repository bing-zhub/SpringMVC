package dao;

import bean.User;
import mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String checkUser(String userId, String pwd) throws Exception {
        if(userId == null || userId.isEmpty()){
            throw new Exception("Please input the userid");
        }
        String sql = "select * from users where userid = ?";
        Object[] args = {userId};
        List<User> user = this.jdbcTemplate.query(sql, new UserMapper(), args);
        if (user.isEmpty())
            throw new Exception("User Not Exist");
        if(!user.get(0).getPwd().equals(pwd))
            throw new Exception("Password Not Match");

        return user.get(0).getUsername();
    }

    public void regUser(User user) throws Exception {
        String sql = "select * from users where userid = ?";
        Object[] args = {user.getUserid()};
        List<User> users = this.jdbcTemplate.query(sql, new UserMapper(), args);
        if (!users.isEmpty())
            throw new Exception("User Exists");
        sql = "INSERT INTO users VALUES (?,?,?)";
        Object[] args1 = {user.getUserid(), user.getUsername(), user.getPwd()};
        this.jdbcTemplate.update(sql, args1);
    }
}
