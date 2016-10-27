
/**
 * 随机生成A-Z，但不能重复；要求时间复杂度为1； 思路：设置一个数组进行，每次输出，在数组中进行标记；通过标记的比对来判定是否重复；
 * 
 * @author 小鑫哦
 *
 */
public class RandomAtoZ {
	public static void main(String[] args) {
		byte[] arr = new byte[26];

		for (int i = 0; i < arr.length;) {
			byte c = (byte) (Math.round(Math.random() * 25) + 65);
			if (arr[c - 65] == 0) {
				arr[c - 65] = 1;
				System.out.println((char) c);
				i++;
			}
		}
	}
}
