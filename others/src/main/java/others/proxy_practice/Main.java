package others.proxy_practice;

import org.springframework.cglib.proxy.Proxy;

public class Main {
    public static void main(String[] args) {
        final KaiTest kaiTest = (KaiTest) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{KaiTest.class}, new RpcfxInvocationHandler());
        System.out.println(kaiTest.get());
    }
}
