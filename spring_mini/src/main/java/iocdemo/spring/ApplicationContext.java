package iocdemo.spring;

import iocdemo.annotation.ExtResource;
import iocdemo.annotation.ExtService;
import iocdemo.utils.ClassUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 手写Spring专题 注解版本注入bean
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ApplicationContext {
	/**
	 * 扫包范围
 	 */
	private String packageName;
	/**
	 * IOC容器
	 */
	private ConcurrentHashMap<String, Object> iocContainer = null;

	public ApplicationContext(String packageName) throws Exception {
		this.packageName = packageName;

		// 1.使用反射机制获取该包下所有打了@ExtService注解的类
		List<Class> listClassesAnnotation = findClassExisService();
		if (listClassesAnnotation == null || listClassesAnnotation.isEmpty()) {
			throw new Exception("没有需要初始化的bean");
		}
		// 2.使用反射机制初始化对象
		iocContainer = initBean(listClassesAnnotation);
		if (iocContainer == null || iocContainer.isEmpty()) {
			throw new Exception("初始化bean为空!");
		}
		// 3.为IOC容器中每个对象中打了@ExtResource注解的属性，自动注入对象
		for (Object object : iocContainer.values()) {
			attriAssign(object);
		}
	}

	// 使用beanID查找对象
	public Object getBean(String beanId) {
		return iocContainer.get(beanId);
	}

	// 使用反射读取类的属性,赋值信息
	public void attriAssign(Object object) throws IllegalArgumentException, IllegalAccessException {
		// 1.获取类的属性是否存在 获取bean注解
		Class classInfo = object.getClass();
		Field[] declaredFields = classInfo.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(ExtResource.class)) {
				// 属性名称
				String name = field.getName();
				// 2.使用属性名称查找bean容器赋值
				Object bean = iocContainer.get(name);
				if (bean != null) {
					// 私有访问允许访问
					field.setAccessible(true);
					// 给属性赋值
					field.set(object, bean);
				}
			}
		}

	}

	// 使用反射机制获取该包下所有的类已经存在bean的注解类
	public List<Class> findClassExisService() throws Exception {
		// 1.使用反射机制获取该包下所有的类
		if (packageName == null || packageName.equals("")) {
			throw new Exception("扫包地址不能为空!");
		}
		// 2.使用反射技术获取当前包下所有的类
		List<Class<?>> classesByPackageName = ClassUtil.getClasses(packageName);
		// 3.存放类上有bean注入注解
		List<Class> exisClassesAnnotation = new ArrayList<>();
		// 4.判断该类上属否存在注解
		for (Class classInfo : classesByPackageName) {
			ExtService extService = (ExtService) classInfo.getDeclaredAnnotation(ExtService.class);
			if (extService != null) {
				exisClassesAnnotation.add(classInfo);
			}
		}
		return exisClassesAnnotation;
	}

	// 初始化bean对象
	public ConcurrentHashMap<String, Object> initBean(List<Class> listClassesAnnotation)
			throws InstantiationException, IllegalAccessException {
		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, Object>();
		for (Class classInfo : listClassesAnnotation) {
			// 初始化对象
			Object newInstance = classInfo.newInstance();
			// 获取父类名称
			String beanId = toLowerCaseFirstOne(classInfo.getSimpleName());
			concurrentHashMap.put(beanId, newInstance);
		}
		return concurrentHashMap;
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

}
