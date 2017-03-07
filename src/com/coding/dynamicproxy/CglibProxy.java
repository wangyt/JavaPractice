package com.coding.dynamicproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		
		System.out.println("+++++++++before " + arg3.getSuperName() + "++++++++++");
		System.out.println(arg1.getName());
		Object resultObject = arg3.invokeSuper(arg0, arg2);
		System.out.println("+++++++++after " + arg3.getSuperName() + "++++++++++");
		return resultObject;
	}

	
}
