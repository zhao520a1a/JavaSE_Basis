package com.xin.net;

import java.net.*;

class UDPClient {
	
	public static void main(String[] args) throws Exception {
		
		//把一个字符串转换为一个字节数组；
		byte[] buf = (new String ("Hello")).getBytes();
		
		//打成一个数据包，注意UDP传输，需要写明SocketAddress【传给谁】(例如下面的它子类InetSocketAdress(IP,port)写的这样);
		DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 5678));
		
		//指明UDPClient是占用的9999端口发信息；
		DatagramSocket ds = new DatagramSocket(9999);
		ds.send(dp);//把包发出去；
		ds.close();
	}

}