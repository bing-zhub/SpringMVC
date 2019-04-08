package cn.bing.common;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
    public void addUser() {
        try {
            Thread.sleep( (int)(Math.random()*1000));
        } catch (InterruptedException e) {
        }
        System.out.println("add User");
    }

    public int removeUser() {
        System.out.println("remove User");
        return 1;
    }
}
