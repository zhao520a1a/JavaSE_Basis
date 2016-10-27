package com.xin.net;

import java.net.*;

class UDPServer {
	public static void main(String[] args) throws Exception {
		//相当于装数据的盒子；
		byte[] buf = new byte[1024];
		//相当于盒子外面的包装；传输都是以它（数据包）为单位来收发的；
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		DatagramSocket ds = new DatagramSocket(5678);
		while(true) {
			ds.receive(dp);//是阻塞式的方法；
			//输出收到的数据；dp.getLength（）返回的是数据包中所收数据所占的空间大小；
			System.out.println(new String(buf, 0, dp.getLength()));
		}
	}
}