package com.xin.beanUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

/**
 * 使用beanUtils操作对象中属性（用第三方的jar包）
 * 
 * @author 小鑫哦
 *
 */
public class Demo1 {

	@Test
	public void test1() throws IllegalAccessException, InvocationTargetException {
		
		/*//我们自己的日期转化器；为了让日期赋到Person类对象的brithday属性上，需要给beanUtils注册一个日期转换器
		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object value) {
				if(value == null) {
					return null;
				}
				if(!(value instanceof String)) {
					throw new ConversionException("只支持String类性的转换！");
				}
				String str = (String)value;
				if(str.trim().equals("")) { //排除输入情况的处理；
					return null;
				}
				
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return dt.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e); //异常链不能断；
				}
			}
			
		}, Date.class);*/
		
		Person p = new Person();
		//通过BeanUtils类设置Person类的属性值；
		BeanUtils.setProperty(p, "name", "刘德华");
		BeanUtils.setProperty(p, "age", 18);
		System.out.println(p.getName());
		System.out.println(p.getAge());
		
		/*
		 * BeanUtils只能直接操作的只支持8中基本数据类型；要是想使用引用类型，则需要给beanUtils注册一个日期转换器
		*/
		String birth = "1995-08-13";
		//beanUtils中已经定义了一些常用转换器；
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		
		BeanUtils.setProperty(p, "birthday", birth);
		System.out.println(p.getBirthday());
		
	}

}
