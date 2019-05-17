package transactiondemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import transactiondemo.transaction.TransactionUtils;

/**
 * 编程式事务演示，在业务代码中手动控制事务的开启、提交、回滚
 * @author swxu_2005@163.com
 * @date 2019/5/15
 */
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TransactionUtils transactionUtils;

  public void add() {
    TransactionStatus transactionStatus = null;
    try {
      // 事务开启
      transactionStatus = transactionUtils.begin();
      UserEntity user = new UserEntity();
      user.setName("zhangsan");
      userRepository.save(user);

      int i = 1 / 0;

      System.out.println("我是add方法");
      user = new UserEntity();
      user.setName("lisi");
      userRepository.save(user);

      // 事务提交
      transactionUtils.commit(transactionStatus);
    } catch (Exception e) {
      e.printStackTrace();
      if (transactionStatus != null) {
        transactionUtils.rollback(transactionStatus);
      }
    }

  }

}
