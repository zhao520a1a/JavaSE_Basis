
import java.util.Random;

public class IdentifyingCode {
	private Random r = new Random();

	// 随机生成四位带数字，字母，汉字的验证码；
	public void getCode() {

		for (int i = 0; i < 4; i++) {
			int j = r.nextInt(3);
			switch (j) {
			case 0:
				System.out.print(r.nextInt(9));
				break;
			case 1:
				char c = (char) (65 + r.nextInt(26));
				System.out.print(c);
				break;
			case 2:
				// 汉字的uincode值在20000-29999之间；
				char h = (char) (20000 + r.nextInt(10000));
				System.out.print(h);
				break;
			}
		}
	}

	public static void main(String[] args) {
		IdentifyingCode id = new IdentifyingCode();
		id.getCode();
	}

}
