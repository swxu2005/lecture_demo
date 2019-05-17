package demo.proxy.static_proxy;

import demo.proxy.IService;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/9
 */
public class TraceProxy implements IService {

  private IService realService;

  public TraceProxy(IService realService) {
    this.realService = realService;
  }

  @Override
  public void sayHello() {
    System.out.println("entering sayHello");

    // 调用真正的原对象的业务方法
    this.realService.sayHello();

    System.out.println("leaving sayHello");
  }

}
