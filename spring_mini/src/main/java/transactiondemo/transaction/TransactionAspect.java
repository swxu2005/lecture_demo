package transactiondemo.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/15
 */
@Component
@Aspect
public class TransactionAspect {
  @Autowired
  private TransactionUtils transactionUtils;

  private ThreadLocal<TransactionStatus> threadLocal = new ThreadLocal<>();

  @Pointcut("@annotation(transactiondemo.annotation.ExtTransctional)")
  public void transactionCut() {

  }

  // 异常通知
  @AfterThrowing("transactionCut()")
  public void afterThrowing() {
    System.out.println("程序已经回滚");
    // 获取程序当前事务 进行回滚
    TransactionStatus status = threadLocal.get();
    transactionUtils.rollback(status);
  }

  // 环绕通知
  @Around("transactionCut()")
  public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("开启事务");
    TransactionStatus status = transactionUtils.begin();
    threadLocal.set(status);

    // 业务方法执行
    proceedingJoinPoint.proceed();

    transactionUtils.commit(status);
    System.out.println("提交事务");
  }

}
