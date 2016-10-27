package com.xin.reflect;

public class Person {
	
	public String name = "无名氏";
	private int age = 0;
	private static String sex = "男";
	
	
	public Person() {
		System.out.println("person");
	}
	
	public Person(String name) {
		this.name = name;
		System.out.println("person" + name);
	}
	
	public Person(String[] s) {
		this.name = s[0];
		this.sex = s[1];
		System.out.println("person：" + name + " "+ sex);
	}
	
	private Person(int age) {
		this.age = age;
		System.out.println("person:" + age);
	}
	
	
	public void speak() {
		System.out.println("我能说话！");
	}
	
	public void speak(String s) {
		System.out.println("我能说话:" + s);
	}
	
	public void speak(String[] s) {
		System.out.print("我能说话:");
		for(String i: s) {
			System.out.println(i);
		}
	}
	
	private void speak(String s, Person p) {
		System.out.println("我能说话:" + s + "to" + p.name);
	}
	
	
	
	
	
	
}
