package cg;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCgAspect implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before Method Invoke");
        methodProxy.invokeSuper(o, objects);
        System.out.println("After Method Invoke");

        return o;
    }
}
