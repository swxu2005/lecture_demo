package demo.proxy.cglib_proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/9
 */
public class SimpleInterceptor implements MethodInterceptor {
  @Override
  public Object intercept(Object object, Method method,
                          Object[] args, MethodProxy proxy) throws Throwable {
    System.out.println("entering " + method.getName());

    // 调用原始方法
    Object result = proxy.invokeSuper(object, args);

    System.out.println("leaving " + method.getName());
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getProxy(Class<T> cls) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(cls);
    enhancer.setCallback(new SimpleInterceptor());
    return (T) enhancer.create();
  }
}
