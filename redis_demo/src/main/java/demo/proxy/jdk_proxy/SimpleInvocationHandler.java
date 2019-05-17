package demo.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/9
 */
public class SimpleInvocationHandler implements InvocationHandler {

  private Object realObj;

  public SimpleInvocationHandler(Object realObj) {
    this.realObj = realObj;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("entering " + method.getName());

    // 真正调用原对象的业务方法
    Object result = method.invoke(realObj, args);

    System.out.println("leaving " + method.getName());
    return result;
  }
}
