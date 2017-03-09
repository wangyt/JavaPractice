package com.coding.threadlocal;

import java.util.Random;

public class ThreadLocalDemo implements Runnable {

	//创建线程局部变量studentLocal，在后面你会发现用来保存Student对象
	private final static ThreadLocal studentLocal = new ThreadLocal();
	//Student student1 = new Student();
	
	@Override
	public void run() {
		accessStudent();
	}

	public static void main(String[] args) {
		ThreadLocalDemo td = new ThreadLocalDemo();
		Thread th1 = new Thread(td, "a");
		Thread th2 = new Thread(td, "b");
		
		th1.start();
		th2.start();
	}

	public void accessStudent() {
		//获取当前线程名字
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + " is Running");
		//产生一个随机数并打印
		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("Thread " + currentThreadName + " set age to: " + age);
		//获取一个Student对象，并将随机数年龄插入到对象属性中	
		Student student1 = getStudent();//观察两种取得student对象方式的不同
		student1.setAge(age);
		System.out.println("Thread " + currentThreadName + " first read age is: " + student1.getAge());
		
		//让线程进入休眠
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//休眠后再次观察该线程读取的对象的属性值是否被修改
		System.out.println("Thread " + currentThreadName + " second read age is: " + student1.getAge());
	}
	
	protected Student getStudent(){
		//获取本地线程变量并强制转换为Student类型
		Student student = (Student)studentLocal.get();
		//线程首次执行此方法时，studentLocal.get()肯定为null
		if (student == null) {
			student = new Student();
			studentLocal.set(student);
		}
		return student;
	}
}
