class YangHui {
	public static void main(String[] args) {
		int[][] a = new int[10][10];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || i == j) {
					a[i][j] = 1;// 先把边界置1
				} else {
					// 杨辉三角的规律公式；
					a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
				}
			}
		}

		for (int i = 0; i < a.length; i++) {
			// 打印左上角空地
			for (int j = 0; j < 2 * (a.length - i); j++) {
				System.out.print(" ");
			}
			// 打印数组
			for (int j = 0; j <= i; j++) {
				System.out.print(a[i][j] + "   ");
			}
			System.out.println();
		}

		for (int i = 0; i < a.length; i++) {
			// 打印右上角空地
			for (int j = 0; j < 2 * (i + 1); j++) {
				System.out.print(" ");
			}
			// 打印数组
			for (int j = 0; j <= a.length - 1 - i; j++) {
				System.out.print(a[a.length - 1 - i][j] + "   ");
			}
			System.out.println();

		}

	}

}