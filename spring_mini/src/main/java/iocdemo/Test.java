package iocdemo;


import iocdemo.service.UserService;
import iocdemo.spring.ApplicationContext;

public class Test {

	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ApplicationContext("iocdemo.service.impl");
		UserService userService = (UserService) app.getBean("userServiceImpl");
		userService.add();
	}

}
