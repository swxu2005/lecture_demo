package transactiondemo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * 事务管理组件
 * @author swxu_2005@163.com
 * @date 2019/5/15
 */
@Component
public class TransactionUtils {

  @Autowired
  private PlatformTransactionManager transactionManager;

  // 开启事务
  public TransactionStatus begin() {
    TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionAttribute());
    return transaction;
  }

  // 提交事务
  public void commit(TransactionStatus transactionStatus) {
    transactionManager.commit(transactionStatus);
  }

  // 回滚事务
  public void rollback(TransactionStatus transactionStatus) {
    transactionManager.rollback(transactionStatus);
  }

}
