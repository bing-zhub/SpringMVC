package cn.edu.zucc.pb;

import cn.edu.zucc.pb.db.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginServiceV1 implements ILogin{

    @Override
    public boolean login(String userid, String password) {
        Connection conn=null;
        try {
            conn = DataBaseManager.getConnection();
            String sql="select password" +
                    " from users" +
                    " where userid = '" + userid + "'";
            System.out.println("LoginServiceV1 SQL:" + sql);
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()){
                String pwd = rs.getString(1);
                if(pwd.equals(password)){
                    return true;
                }
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
        return false;
    }
}
