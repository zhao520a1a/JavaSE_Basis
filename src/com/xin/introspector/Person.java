package com.xin.introspector;

public class Person {
	//这只是字段，并不是属性；
	private String name;
	private int age;
	
	//这里Sex,虽然它不是一个字段但它是一个属性，因为它还有getter方法；
	public String getSex() {
		return null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
