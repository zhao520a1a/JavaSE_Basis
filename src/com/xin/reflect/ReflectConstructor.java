package com.xin.reflect;

import java.lang.reflect.Constructor;

import org.junit.Test;

/**
 * 反射类中的构造函数， 创建对象；
 * 
 * @author 小鑫哦
 *
 */
public class ReflectConstructor {

	@Test
	public void test2() throws Exception {
		/*
		 * 加载类（获得类的字节码class文件）：有三种方法： 1.Class.forName("类名"); 2.new
		 * 类名().getClass(); 3.类名.class;
		 */

		Class clazz1 = Class.forName("com.xin.reflect.Person"); // 必须是字符串版的类全名
		// Class clazz2 = new Person().getClass();
		// Class clazz3 = Person.class;

		// 得到Person类的默认构造函数
		Constructor c1 = clazz1.getConstructor(null);
		Person p1 = (Person) c1.newInstance(null); // 用其创建出Person类的对象；

		// 得到Person类的形参为String的构造函数
		Constructor c2 = clazz1.getConstructor(String.class);
		Person p2 = (Person) c2.newInstance("阿衰");

		// 得到Person类的形参为数组的构造函数
		Constructor c3 = clazz1.getConstructor(String[].class);

		/*
		 * 记住：当方法的形参是数组时，要注意：由于jdk为了兼容会导致，你写一个数组，而jdk会将数组中的元素拆分成多个形参，
		 * 不会当作一个数组形参使用； 有两种解决方法： 1.在外面再包一层数组，jdk拆开后正好是一个数组；
		 * 2.将数组强转成一个对象，让jdk将它当作一个对象看待；
		 */
		// Person person3 = (Person) c3.newInstance(new Object[] { new String[]
		// { "阿衰", "男" } });
		Person person3 = (Person) c3.newInstance((Object) new String[] { "阿衰", "男" });

		// 得到Person类的私有的构造函数
		Constructor c4 = clazz1.getDeclaredConstructor(int.class);
		c4.setAccessible(true); // 因为是私有要强开权限，暴力反射；才能在类外使用；
		Person p4 = (Person) c4.newInstance(18);

		// 创建对象的另一种形式；但是它内部只能调用无参的默认构造函数来创建对象；
		Person p5 = (Person) clazz1.newInstance();

	}

}
