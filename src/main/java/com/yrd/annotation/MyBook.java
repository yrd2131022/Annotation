package com.yrd.annotation;
/*
 * * 自定义注解
		* 格式：
			元注解
			public @interface 注解名称{
				属性列表;
			}
	属性的格式：
		  格式1：数据类型  属性名();
		  格式2：数据类型  属性名() default 默认值;
	属性的返回值类型有下列取值
					* 基本数据类型
					* String
					* 枚举
					* 注解
					* 以上类型的数组	
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@MyTest1("/deleteBook.action")
//@MyTest(value = "/deleteBook.action",age = 20)
@Book(authors = { "xiaoming","lili","dawei" }, name = "<精通java基础>", price = 20)
public class MyBook {
	
	@MyTest1("b")
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		
		int age=12;
	}
}
//自定义一个注解
@interface Book {
	String name();
	String[] authors();
	double price();
	String address() default "福建";

}
/*
 * .注解的特殊属性名称：value
 * .1如果只有一个属性需要赋值，并且属性的名称是value，则value可以省略，直接定义值即可。
 * .2但是如果有多个属性，且多个属性没有默认值，那么value是不能省略的。
 */
@interface MyTest1 {
	String value();
	int age() default 10;
}
////////////////////////////////////////////////////////////////////////////////////////////
/*
 * * 元注解：用于描述注解的注解
			* @Target：描述注解能够作用的位置
				* ElementType取值：
					* TYPE：可以作用于类上
					* METHOD：可以作用于方法上
					* FIELD：可以作用于成员变量上
			* @Retention：描述注解被保留的阶段
				* @Retention(RetentionPolicy.RUNTIME)：当前被描述的注解，会保留到class字节码文件中，并被JVM读取到
				* 可使用的值定义在RetentionPolicy枚举类中，常用值如下：
				* SOURCE:注解只作用在源码阶段，生成的字节码文件中不存在
				* CLASS:注解作用在源码阶段，字节码文件阶段，运行阶段不存在，默认值。
				* RUNTIME:注解作用在源码阶段，字节码文件阶段，运行阶段
			* @Documented：描述注解是否被抽取到api文档中
			* @Inherited：描述注解是否被子类继承
			* 
			* .小结：
			* @Target约束自定义注解可以标记的范围
			* @Retention用于约束自定义注解的存活范围
 */

//申明只能注解方法和类
@Target(value = { ElementType.METHOD,ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation{
	
}

@MyAnnotation
class Annotation{
	
//	@MyAnnotation
	@SuppressWarnings("unused")
	private int age;
	
//	@MyAnnotation
	public Annotation() {
		
	}
	
	@MyAnnotation
	public void save() {
		System.out.println("save");
	}
	
}

////////////////////////////////////////////////////////////////////////////////////////////




