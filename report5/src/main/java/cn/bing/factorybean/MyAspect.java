package cn.bing.factorybean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAspect implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        checkPermission();
        Object o = methodInvocation.proceed();
        log();
        return o;
    }

    public void checkPermission(){
        System.out.println("检查权限....");
    }

    public void log(){
        System.out.println("记录日志....");
    }
}
