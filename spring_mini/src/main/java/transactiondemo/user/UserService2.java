package transactiondemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transactiondemo.annotation.ExtTransctional;

/**
 * 声明式事务演示，在业务代码中手动控制事务的开启、提交、回滚
 * @author swxu_2005@163.com
 * @date 2019/5/15
 */
@Service
public class UserService2 {
  @Autowired
  private UserRepository userRepository;

  @ExtTransctional
  public void add() {
      UserEntity user = new UserEntity();
      user.setName("zhangsan");
      userRepository.save(user);

//      int i = 1 / 0;

      System.out.println("我是add方法");
      user = new UserEntity();
      user.setName("lisi");
      userRepository.save(user);
  }

}
