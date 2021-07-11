package com.yrd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
 *  模拟Junit测试的注释@Test，
 *  首先需要编写自定义注解@MyTest，并添加元注解，保证自定义注解只能修饰方法，且在运行时可以获得。
 */
public class TestDemo {
	@MyTest
	public void test01() {
		System.out.println("======test01========");
	}
	
	public void test02() {
		System.out.println("======test02========");
	}
	
	@MyTest
	public void test03() {
		System.out.println("======test03========");
	}
	
	@MyTest
	public void test04() {
		System.out.println("======test04========");
	}
	
	public static void main(String[] args) throws Exception {
		//模拟测试类的启动按钮，实现有注解标记的方法就要触发执行
		//1.获取Class对象
		Class<TestDemo> c = TestDemo.class;
		//创建实例对象
		Constructor<TestDemo> constructor = c.getConstructor();
		TestDemo obj = constructor.newInstance();
		//2.获取类中的全部方法对象
		Method[] methods = c.getDeclaredMethods();
		//3.遍历全部方法，有注解就触发执行
		for (Method method : methods) {
			if(method.isAnnotationPresent(MyTest.class)) {
				//触发此方法执行
				method.invoke(obj);
			}
		}
	}

}


@Target( ElementType.METHOD )
@Retention(RetentionPolicy.RUNTIME)
@interface MyTest{
	
}





