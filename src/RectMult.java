
//两个矩阵相乘；三个for循环
class RectMult {
	public static void main(String[] args){
		int[][] a = {{1, 2},{3, 4}};
		int[][] b = {{1, 2},{3, 4}};
		
		int[][] c = new int[2][2];
		
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a[i].length; j++){
				int t = 0;
				//按照矩阵相乘的规则得出的规律公式；
				for(int k=0; k<a.length; k++) {
					t += a[i][k] * b[k][j];
				}
				c[i][j] = t;
			}
		}
		
		for(int[] i: c) {
			for(int j: i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	
	}
}