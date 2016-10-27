/*
	解决约瑟夫环问题；已知n个人(以编号1，2，3...n分别表示)围坐在一张圆桌周围。
	从编号为k的人开始报数，数到m的那个人出列;他的下一个人又从1开始报数，
	数到m的那个人又出列;依此规律重复下去，直到圆桌周围的人全部出列。
	
	先说说我做这题的思路：
       1、创建一个含有100个元素的集合，元素从1到100。（分别对应这100个人）
       2、从1数到14算一圈，则相当于走了99个圈，每走一圈从集合里删除一个元素。
       3、走完这99圈以后，集合里剩下的那个元素就是最后剩下的人
	本题最核心的还是求每次循环时需要删除的那个元素的下标。
*/

import java.util.*;

class JosephHoop{
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("请输入总人数：");
		int total = s.nextInt();
		
		System.out.print("请输入从1报到哪个数字退出：");
		int endNum = s.nextInt();
		
		josephHoop(total, endNum);
	}
	
	public static void josephHoop (int total, int endNum) {
		
		List<Integer> all = new LinkedList<Integer>();
		for(int i=0; i<total; i++) {
			all.add(i);//将人从零开始编号；
		}
		
		int n = 0;
		for(int i=0; i<total-1; i++) {
			/*
				每次循环时，求得将要退出的人在集合中的下标；
				取余是为了循环；
			*/
			n = (n + endNum - 1) % all.size();//从1开始报数；
			//n = (n + endNum) % all.size(); 从0开始报数
			System.out.println(all.get(n));
			all.remove(n); 
			
		}
		
		System.out.println("最后剩下的是第" + all.get(0) + "个人");
		
	
	}
	
	
		
}