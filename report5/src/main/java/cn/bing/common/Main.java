package cn.bing.common;

import cg.MyCgAspect;
import net.sf.cglib.proxy.Enhancer;
import offical.MyAspect;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        IUserDao i = (IUserDao) Proxy.newProxyInstance(
                IUserDao.class.getClassLoader(),
                new Class[]{IUserDao.class},
                new MyAspect(new UserDaoImpl()));
        System.out.println("-------offical------");
        i.addUser();
        i.removeUser();

        System.out.println("-------CGLIB-------");
        MyCgAspect myCgAspect = new MyCgAspect();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDaoImpl.class);
        enhancer.setCallback(myCgAspect);

        IUserDao i1 = (IUserDao) enhancer.create();
        i1.addUser();
        i1.removeUser();
    }
}
