package demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/6
 */
@Configuration
public class RedisConfig {
  @Autowired
  private RedisConnectionFactory factory;
  @Autowired
  private ObjectMapper mapper;

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);

    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(mapper));

    return redisTemplate;
  }

}
