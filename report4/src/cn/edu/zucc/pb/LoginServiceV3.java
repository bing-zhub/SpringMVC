package cn.edu.zucc.pb;

import cn.edu.zucc.pb.bean.User;
import cn.edu.zucc.pb.v3.dao.Dao;

public class LoginServiceV3 implements ILogin{

    @Override
    public boolean login(String userid, String password) {
        Dao<User> dao = new Dao<User>();

        User user = dao.getEntity(userid, User.class.getName());
        if(user != null && user.getPwd().equals(password)){
            return true;
        }

        return false;
    }
}
