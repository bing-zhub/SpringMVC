package service;

import bean.User;
import dao.UserDao;

public class UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String checkUser(String userid, String pwd) throws Exception {
        return this.userDao.checkUser(userid, pwd);
    }

    public void regUser(User user) throws Exception {
        this.userDao.regUser(user);
    }
}
