package demo;

import demo.service.RedisService;
import demo.service.RedisStringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisStringServiceTest {

  @Autowired
  private RedisStringService redisStringService;

  @Autowired
  private RedisService redisService;

  @Test
  public void opsForValueDemo() throws Exception {
    redisStringService.opsForValueDemo();
  }

  @Test
  public void redisServiceTest() throws Exception {
    redisService.opsForValueDemo();
  }
}
