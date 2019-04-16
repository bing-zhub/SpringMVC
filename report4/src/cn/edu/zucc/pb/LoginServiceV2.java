package cn.edu.zucc.pb;

import cn.edu.zucc.pb.bean.User;
import cn.edu.zucc.pb.dao.UserDao;


public class LoginServiceV2 implements ILogin{

    @Override
    public boolean login(String userid, String password) {
        UserDao dao = new UserDao();

        User user = dao.getUser(userid);
        if(user != null && user.getPwd().equals(password)){
            return true;
        }

        return false;
    }
}
