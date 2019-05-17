package demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/6
 */
@Data
public class Student implements Serializable {
  private Long id;
  private String name;
  private Integer age;
  private String sex;

  public Student(Long id, String name, Integer age, String sex) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.sex = sex;
  }
}
