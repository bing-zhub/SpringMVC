package cn.edu.zucc.pb.dao;

import cn.edu.zucc.pb.bean.User;
import cn.edu.zucc.pb.db.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    public User getUser(String userid){
        Connection conn=null;
        User user = new User();
        try {
            conn = DataBaseManager.getConnection();
            String sql="select userid, username, pwd" +
                    " from users" +
                    " where userid = '" + userid + "'";
            System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " SQL:" + sql);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()){
                String name = rs.getString(2);
                String pwd = rs.getString(3);
                user.setUsername(name);
                user.setUserid(userid);
                user.setPwd(pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return user;
    }
}
