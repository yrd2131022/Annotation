package com.yrd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

/*
 * 什么是注解解析
        * 使用Java技术获得注解上数据的过程则称为注解解析。
    与注解解析相关的接口
        * Annotation: 注解类，该类是所有注解的父类。
        * AnnotatedElement:该接口定义了与注解解析相关的方法
             T getAnnotation(Class<T> annotationClass) 根据注解类型获得对应注解对象
             Annotation[]	getAnnotations()
                * 获得当前对象上使用的所有注解，返回注解数组，包含父类继承的
             Annotation[]	getDeclaredAnnotations()
                * 获得当前对象上使用的所有注解，返回注解数组,只包含本类的
             boolean	isAnnotationPresent(Class<Annotation> annotationClass)
                * 判断当前对象是否使用了指定的注解，如果使用了则返回true，否则false

        获取注解数据的原理
            * 注解作用在哪个成员上就会得该成员对应的对象来获得注解
                * 比如注解作用成员方法，则要获得该成员方法对应的Method对象
                * 比如注解作用在类上，则要该类的Class对象
                * 比如注解作用在成员变量上，则要获得该成员变量对应的Field对象。
            * Field,Method,Constructor，Class等类都是实现了AnnotatedElement接口
            * 
 #### 3.5.4.1 需求说明

1. 定义注解Book，要求如下：
   - 包含属性：String value()   书名
   - 包含属性：double price()  价格，默认值为 100
   - 包含属性：String[] authors() 多位作者  
   - 限制注解使用的位置：类和成员方法上
   - 指定注解的有效范围：RUNTIME
2. 定义BookStore类，在类和成员方法上使用Book注解
3. 定义TestAnnotation测试类获取Book注解上的数据
 */
public class ParseAnnotation {

	public static void main(String[] args) throws Exception {
		//1.定位Class类对象
		Class<BookStore> c = BookStore.class;
		//2.判断这个类(BookStore)是否使用了某个(MyBookParse)注解
		if(c.isAnnotationPresent(MyBookParse.class)) {
			//3.获取这个注解对象
			MyBookParse myBookParse = c.getDeclaredAnnotation(MyBookParse.class);
			
			System.out.println(myBookParse.value());
			System.out.println(Arrays.toString(myBookParse.authors()));
			System.out.println(myBookParse.price());
		}
		
		//4.定位方法对象
		Method showBookMethod = c.getDeclaredMethod("showBook");
		//5.判断这个方法(showBook)是否使用了某个(MyBookParse)注解
				if(showBookMethod.isAnnotationPresent(MyBookParse.class)) {
					//3.获取这个注解对象
					MyBookParse myBookParse = showBookMethod.getDeclaredAnnotation(MyBookParse.class);
					
					System.out.println(myBookParse.value());
					System.out.println(Arrays.toString(myBookParse.authors()));
					System.out.println(myBookParse.price());
				}

	}

}


@Target(value = { ElementType.TYPE,ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyBookParse{
	String value();
	double price() default 100;
	String[] authors();
}

@MyBookParse(authors = { "曹雪芹","波波沙" }, value = "红楼梦")
class BookStore{
	
	@MyBookParse(authors = { "DaiWei","XiLi" }, value = "《MyBatis持久层框架》",price = 56)
	public void showBook() {
		
	}
}




