class ForHome7 {
	public static void main(String[] args) {
		int[][] a = new int[4][16];

		// 打印上半边；
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				// 通过找寻的行列之间的关系来判断是否打印空地
				if (i > 0 && j >= 2 * (a.length - i) && j <= 2 * (a.length - i) + 3 + 4 * (i - 1)) {
					System.out.print(" ");
				} else {
					System.out.print(8 - 2 * i);
				}
			}
			System.out.println();
		}

		// 打印下半边；
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				// 通过找寻的行列之间的关系来判断是否打印空地
				if (i < a.length && j >= (i + 1) * 2 && j <= (i + 1) * 2 + 3 + 4 * (2 - i)) {
					System.out.print(" ");
				} else {
					System.out.print(2 * i + 2);
				}
			}
			System.out.println();
		}

	}
}