package cn.bing.factorybean;

import cn.bing.common.UserDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryTest {
    public static void main(String[] args) {
        String xmlPath = "cn/bing/factorybean/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserDaoImpl userDao = (UserDaoImpl)applicationContext.getBean("userDaoProxy");
        userDao.addUser();
        userDao.removeUser();
    }
}

