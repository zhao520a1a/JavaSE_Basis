package com.xin.reflect;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * 反射类中的成员变量
 * 
 * @author 小鑫哦
 */

public class ReflectField {

	@Test
	public void test2() throws Exception {
		Person p = new Person();
		Class clazz = Class.forName("com.xin.reflect.Person");

		/*
		 * 得到Person类的公有属性name; public String name = "无名氏";
		 */
		Field fname = clazz.getField("name");
		// 可以判断一下属性的类型是否与预想的一样；
		if (fname.getType().equals(String.class)) {
			String name = (String) fname.get(p); // 获得属性name的值；
			System.out.println(name);

			fname.set(p, "阿信");// 修改属性name的值；
			String name1 = (String) fname.get(p); // 获得属性name的值
			System.out.println(name1);
		}

		/*
		 * 得到Person类的私有属性age; private int age = 0;
		 */
		Field fage = clazz.getDeclaredField("age");
		fage.setAccessible(true); // 代开权限，强力反射；
		int age = fage.getInt(p);
		System.out.println(age);

		/*
		 * 得到Person类的静态私有属性sex; private static String sex = "男";
		 */
		Field fsex = clazz.getDeclaredField("sex");
		fsex.setAccessible(true);
		String sex = (String) fsex.get(null); // 因为是stati所以是可以不用写对象；
		System.out.println(sex);

	}

}
