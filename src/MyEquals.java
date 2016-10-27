
/**
 * 重写object类的equals方法；让其比较两个对象中的值，而不是两者的引用； 要满足：自反性，对称性， 传递性，一致性，非空性；
 * 
 * @author 小鑫哦
 *
 */
public class MyEquals {

	public static void main(String[] args) {
		MyDate d1 = new MyDate(2016, 4, 28);
		MyDate d2 = new MyDate(2016, 4, 28);
		System.out.println(d1.equals(d2));
		System.out.println(d1.equals(d1));
		System.out.println(d1.equals(null));
	}
}

class MyDate {
	private int year;
	private int month;
	private int day;

	public MyDate(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	// 重写object类的equals方法；
	@Override
	public boolean equals(Object obj) {
		if (this == obj) { // 自反性
			return true;
		}
		if (obj == null) { // 非空性
			return false;
		}
		if (this.getClass() != obj.getClass()) { // 得到运行时类，判断来两个对象是否为同一个类
			return false;
		}
		MyDate that = (MyDate) obj; // 必须将其强转为当前的对象类，否则无法比较其中的成员变量；
		// 比较其中的成员变量
		return this.year == that.year && this.month == that.month && this.day == that.day;

	}
}