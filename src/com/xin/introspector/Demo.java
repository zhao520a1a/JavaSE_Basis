package com.xin.introspector;

import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class Demo {

	@Test // 得到bean所有属性；
	public void test1() throws IntrospectionException {
		// 得到了Bean;这里即为Person类
		BeanInfo info1 = Introspector.getBeanInfo(Person.class);
		// 下面就是在Bean中去除了父类Object中的属性
		BeanInfo info2 = Introspector.getBeanInfo(Person.class, Object.class);
		// 得到Bean的对所有属性的描述；
		PropertyDescriptor[] pds = info1.getPropertyDescriptors();
		// 打印属性列表中属性的名称；
		for (PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
		}
	}

	@Test // 操纵bean的得到属性：age
	public void test2()
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person p = new Person();
		PropertyDescriptor pd = new PropertyDescriptor("age", Person.class);

		// 得到当前操纵属性的类型；
		System.out.println(pd.getPropertyType());
		// 得到当前操纵属性的写方法；
		Method method = pd.getWriteMethod();
		method.invoke(p, 18);
		// 得到当前操纵属性的读方法；
		method = pd.getReadMethod();
		System.out.println(method.invoke(p));

	}
}
