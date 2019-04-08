package offical;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyAspect implements InvocationHandler{

    private Object usrImpl;

    public MyAspect(Object obj){
        this.usrImpl = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        check();
        Long before = System.currentTimeMillis();
        Object ans = method.invoke(usrImpl, args);
        Long after = System.currentTimeMillis();
        System.out.println("ºÄÊ±:"+(after - before)+"ms");
        log();
        return ans;
    }

    private void check(){
        System.out.println("check");
    }

    private void log(){
        System.out.println("log");
    }
}

