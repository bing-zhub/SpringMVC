package cn.bing.aspectj.annotation;

import cn.bing.common.IUserDao;
import cn.bing.common.UserDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotationAspectj {
    public static void main(String[] args) {
        String xmlPath = "cn/bing/aspectj/annotation/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        IUserDao userDao = (IUserDao) applicationContext.getBean("userDao");
        userDao.addUser();
    }
}
