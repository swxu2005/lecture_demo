package demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/6
 */
@Service
public class RedisStringService {
  private static final String REDIS_KEY_FOR_VALUE = "demo:string:value";
  private static final String REDIS_KEY_FOR_LIST = "demo:string:list";
  private static final String REDIS_KEY_FOR_HASH = "demo:string:hash";

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void opsForValueDemo() throws Exception {
    redisTemplate.opsForValue().set(REDIS_KEY_FOR_VALUE, "some words here");
    String str = redisTemplate.opsForValue().get(REDIS_KEY_FOR_VALUE);
    System.out.println(str);

    List<String> testStrings = new ArrayList<>();
    testStrings.add("aaaa");
    testStrings.add("bbbb");
    testStrings.add("cccc");
    testStrings.add("dddd");
    redisTemplate.opsForList().rightPushAll(REDIS_KEY_FOR_LIST, testStrings);
    List<String> allStrings = redisTemplate.opsForList().range(REDIS_KEY_FOR_LIST, 0, -1);
    System.out.println(allStrings);

    List<Student> students = new ArrayList<>();
    students.add(new Student(1001L, "zhangsan", 18, "male"));
    students.add(new Student(1002L, "lisi", 19, "female"));
    students.add(new Student(1003L, "wangwu", 20, "male"));
    students.add(new Student(1004L, "zhaoliu", 21, "female"));
    redisTemplate.opsForValue().set(REDIS_KEY_FOR_VALUE, objectMapper.writeValueAsString(students));

    Map<Long, Student> studentMap = students.stream()
        .collect(Collectors.toMap(Student::getId, t -> t));
    redisTemplate.opsForValue().set(REDIS_KEY_FOR_VALUE, objectMapper.writeValueAsString(studentMap));
  }
}
