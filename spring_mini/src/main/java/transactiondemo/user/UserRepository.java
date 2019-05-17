package transactiondemo.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author swxu_2005@163.com
 * @date 2019/5/15
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
