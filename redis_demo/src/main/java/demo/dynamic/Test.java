package demo.dynamic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author swxu_2005@163.com
 * @date 2019/4/16
 */
public class Test {
  public static void main(String[] args) {
    Class clazz = String.class;

    String a = "fffff";
    Class clazz1 = a.getClass();

    try {
      Class clazz2 = Class.forName("java.lang.Integer");
      Field[] declaredFields = clazz2.getDeclaredFields();
      for (Field field : declaredFields) {
        String fieldName = field.getName();
        field.setAccessible(true);
        field.set(a, "bbbbb");
        Annotation[] annotations = field.getAnnotations();
        if (!field.isAnnotationPresent(JsonIgnore.class)) {
          Object fieldValue = field.get(a);

        }


      }
      Field value = clazz2.getDeclaredField("value");

      Method[] methods = clazz2.getMethods();
      for (Method method : methods) {
        method.invoke(a,null);
      }

      String s = (String) clazz2.newInstance();



    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
  }
}
