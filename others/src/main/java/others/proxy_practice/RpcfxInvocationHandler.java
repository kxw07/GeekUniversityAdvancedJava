package others.proxy_practice;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class RpcfxInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("Method: " + method.getName());
        return "invoke";
    }
}
