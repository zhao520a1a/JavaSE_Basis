package com.xin.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 反射类中的类中成员方法；
 * 
 * @author 小鑫哦
 */
public class ReflectMethod {

	@Test
	public void Test3() throws Exception {
		Person p = new Person();
		Class clazz = Person.class;

		/*
		 * 反射出Person类中public void speak();
		 */
		Method m1 = clazz.getMethod("speak", null);
		m1.invoke(p); // 执行该方法；

		/*
		 * 反射出Person类中public void speak(String s);
		 */
		Method m2 = clazz.getMethod("speak", String.class);
		m2.invoke(new Person(), "阿哲");

		/*
		 * 反射出Person类中public void speak(String s);
		 */
		Method m3 = clazz.getMethod("speak", String[].class);
		/*
		 * 记住：当方法的形参是数组时，要注意：由于jdk为了兼容会导致，你写一个数组，而jdk会将数组中的元素拆分成多个形参，
		 * 不会当作一个数组形参使用； 有两种解决方法： 1.在外面再包一层数组，jdk拆开后正好是一个数组；
		 * 2.将数组强转成一个对象，让jdk将它当作一个对象看待；
		 */
		m3.invoke(new Person(), new Object[] { new String[] { "说点啥呢", "啥也不想说" } });
		// m3.invoke(new Person(),(Object) new String[]{"说点啥呢", "啥也不想说"});

		/*
		 * 反射出Person类中private void speak(String s, Person p);
		 */
		Method m4 = clazz.getDeclaredMethod("speak", String.class, Person.class);
		m4.setAccessible(true); // 暴力反射，打开权限；
		m4.invoke(p, "我饿了", p);

	}

}
