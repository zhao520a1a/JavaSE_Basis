import java.util.Arrays;

//枚举类型功能练习；
public enum ESeason {
	SPRING(), SUMMER(), // 枚举类型的第一种写法

	FALL() { // 枚举类型的第二种写法：只能写默认，不能有参数,重写了ok方法，实现了多态；
		int i = 123;

		public void ok() {
			System.out.println(i + " FALL-ok");
		}
	},
	WINTER() {
	};

	public void ok() {
		System.out.println("ok");
	}

	public static void SetSeason(ESeason e) {
		System.out.println("设置当前季节是：" + e);
	}

	public void getSeason() {
		System.out.println("返回当前季节为：" + this);
	}

	public static void main(String[] args) {
		ESeason.SetSeason(ESeason.SPRING);

		ESeason.SPRING.getSeason();
		ESeason.valueOf("SPRING").getSeason();
		ESeason.valueOf(ESeason.class, "SPRING").getSeason();

		ESeason[] earr = ESeason.values();
		System.out.println(Arrays.toString(earr));

		ESeason.FALL.ok();
		ESeason.WINTER.ok();
	}
}