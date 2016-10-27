import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * 对字符串进行加密； 先对字符串进行MD5加密形成字节数组，将字节数组转换成16进制的大写字符；
 * 
 * @author 小鑫哦
 *
 */
public class StringEncrypt {
	private final static String[] hexArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/** 根据指定的字符串，创建加密后的字符串 */
	public static String creatEncryptPassword(String s) {
		return encryptByMD5(s);
	}

	/** 对指定的字符串进行MD5加密 */
	private static String encryptByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有MD5算法的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组转化为十六进制的字符串
				String resultString = byteArrayToHex(results);
				// 最后返回转为大写的字符串；
				return resultString.toUpperCase();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/** 将字节数组转换成16进制，并以字符串的形式返回 */
	private static String byteArrayToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(byteToHex(b[i]));
		}
		return sb.toString();
	}

	/** 将一个字节转换成16进制，并以字符串的形式返回 */
	private static String byteToHex(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexArray[d1] + hexArray[d2];// 一个字节（8bit）转化为两个十六进制(8bit)的数字；
	}

	/** 检验输入的密码是否正确 */
	public static boolean verificationPassword(String password, String s) {
		return password.equals(encryptByMD5(s));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		String password = StringEncrypt.creatEncryptPassword(s);
		System.out.println("对password=" + s + "使用MD5算法加密后的字符串如下：\n  " + password);
		String s1 = scanner.next();
		System.out.println(s1 + "是正确的密码吗？" + StringEncrypt.verificationPassword(password, s1));

	}

}
