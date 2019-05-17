package demo.proxy;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/9
 */
public class RealService implements IService {

  @Override
  public void sayHello() {
    System.out.println("hello");
  }

}
