package cn.edu.zucc.pb.db;

import java.sql.Connection;

public class DataBaseManager {
    private static final String jdbcUrl="jdbc:mysql://115.159.98.171:3306/bookstore?useUnicode=true&characterEncoding=utf-8";
    private static final String dbUser="root";
    private static final String dbPwd="924219829";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws java.sql.SQLException{
        return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
    }

}
