package demo.service;

import demo.entity.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/6
 */
@Service
public class StudentService {

  @Cacheable(cacheNames = "students", key = "#id")
  public Student getOneStudent(Long id) {
    System.out.println("尚未缓存，进入方法");
    return new Student(id, "qqqq", 22, "male");
  }

  @CacheEvict(cacheNames = "students", key = "#student.id")
  public void updateStudent(Student student) {
    // ....
  }

}
