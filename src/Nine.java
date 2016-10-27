public class Nine {
	public static void main(String[] args) {
		for (int i = 1, j = 1; j <= 9; i++) {
			System.out.print(i + "*" + j + "=" + i * j + " ");
			if (i == j) {
				i = 0;// 因为出了if语句作用域，之后，先走i++语句，因此如果i从1开始，必须先置为0
				j++;
				System.out.println();
			}
		}
	}
}
