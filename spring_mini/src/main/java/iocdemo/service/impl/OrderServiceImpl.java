package iocdemo.service.impl;


import iocdemo.annotation.ExtService;
import iocdemo.service.OrderService;

@ExtService
public class OrderServiceImpl implements OrderService {

	@Override
	public void addOrder() {
		System.out.println("addOrder");
	}

}
