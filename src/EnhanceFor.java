import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

/*
 * jdk5.0后加了增加for循环，它只能读数据，不能写数据；
 * 适用对象：数组和继承了Iterator接口的接口或类（如：Set，List等），但是Map不能直接有增强for循环；
 * 如何用，将往下看；
 */

public class EnhanceFor {

	@Test
	public void test() {
		HashMap map = new LinkedHashMap(); // 用LinkedHashMap能让数据有序；
		map.put(1, "AAAA");
		map.put(2, "BBBB");
		map.put(3, "CCCC");

		// 第一种方法,用Map.entrySet()；
		for (Object obj : map.entrySet()) {
			Map.Entry entry = (Entry) obj;
			int key = (int) entry.getKey(); // 得到Map中的键
			String value = (String) entry.getValue();// 得到Map中值
			System.out.println(key + "---" + value);
		}

		// 第二种方法 ,Map.KeySet();
		for (Object obj : map.keySet()) {
			int key = (int) obj;
			String value = (String) map.get(key);
			System.out.println(key + "---" + value);
		}

	}

}
