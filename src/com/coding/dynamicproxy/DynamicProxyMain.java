package com.coding.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxyMain {

	//http://blog.csdn.net/heyutao007/article/details/49738887
	public static void main(String[] args) {
		
		//JDK动态代理实现
		UserService userService = new UserServiceImpl();
		InvocationHandler invocationHandler = new MyInvocationHandler(userService);
		UserService userServiceProxy = (UserService)Proxy.newProxyInstance(
				userService.getClass().getClassLoader(), 
				userService.getClass().getInterfaces(),
				invocationHandler);
		
		System.out.println(userServiceProxy.getName(1));
		System.out.println(userServiceProxy.getAge(1));
	}

}
