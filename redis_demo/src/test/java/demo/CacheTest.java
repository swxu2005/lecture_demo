package demo;

import demo.entity.Student;
import demo.service.StudentService;
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
public class CacheTest {
  @Autowired
  private StudentService studentService;

  @Test
  public void test() {
    Student stu1 = studentService.getOneStudent(1001L);
    Student stu2 = studentService.getOneStudent(1002L);
    Student stu1_1 = studentService.getOneStudent(1001L);
    System.out.println(stu1);
    System.out.println(stu2);
    System.out.println(stu1_1);

  }
}
