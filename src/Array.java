import java.util.*;

class Array {

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println("maxValue:" + maxValue(a));
		System.out.println("avgValue:" + avgValue(a));

		disordered(a);
		System.out.println("颠倒后:");
		for (int i : a) {
			System.out.print(i + " ");
		}

		int[] b = { 10, 11, 12, 13, };
		System.out.println('\n' + "复制后");
		// 数组复制
		System.arraycopy(a, 3, b, 1, 2);
		for (int i : b) {
			System.out.print(i + " ");
		}

	}

	// Array中最大值
	public static int maxValue(int[] a) {
		int max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}

	// Array中平均值
	public static int avgValue(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum / a.length;
	}

	// Array中反转
	public static int[] disordered(int[] a) {

		for (int i = 0; i < a.length / 2; i++) {
			int t = a[i];
			a[i] = a[a.length - 1 - i];
			a[a.length - 1 - i] = t;
		}
		return a;
	}

}