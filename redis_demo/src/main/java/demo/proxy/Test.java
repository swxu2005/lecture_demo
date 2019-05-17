package demo.proxy;

import demo.proxy.IService;
import demo.proxy.RealService;
import demo.proxy.RealService2;
import demo.proxy.cglib_proxy.SimpleInterceptor;
import demo.proxy.jdk_proxy.SimpleInvocationHandler;
import demo.proxy.static_proxy.TraceProxy;

import java.lang.reflect.Proxy;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/9
 */
public class Test {
  public static void main(String[] args) {
    IService realService = new RealService();

//    // 1. 静态代理
//    IService proxyService = new TraceProxy(realService);
//    proxyService.sayHello();

//    // 2. JDK动态代理
//    IService proxyService2 = (IService) Proxy.newProxyInstance(
//        IService.class.getClassLoader(),
//        new Class<?>[] { IService.class },
//        new SimpleInvocationHandler(realService)
//    );
//    proxyService2.sayHello();

    // 3. cglib动态代理
    RealService2 realService2 = SimpleInterceptor.getProxy(RealService2.class);
    realService2.sayHello();
  }
}
