package transactiondemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import transactiondemo.user.UserService;
import transactiondemo.user.UserService2;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;
  @Autowired
  private UserService2 userService2;

  /**
   * 编程式事务测试
   */
  @Test
  public void test() {
    // 在add方法中打断点调试，看事务回滚的效果
    userService.add();
  }

  /**
   * 声明式事务测试
   */
  @Test
  public void test2() {
    userService2.add();
  }
}
