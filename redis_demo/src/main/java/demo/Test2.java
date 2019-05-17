package demo;

import java.util.List;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/16
 */
public class Test2 {
  public static void main(String[] args) throws Exception {
    List list = null;

    Class clazz = Class.forName("java.util.ArrayList");
    list = (List) clazz.newInstance();

  }
}
