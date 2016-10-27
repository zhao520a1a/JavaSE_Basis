package com.xin.net;


/*
TCP网络编程:
2016.3.18
熟悉java基础的TCP的Socke_programing;

*/

import java.net.*;
import java.io.*;

public class TCPServer {
	
		public static void main(String[] args)  throws Exception {
				ServerSocket ss = new ServerSocket(8888);
				while (true) {
						Socket s = ss.accept();//是阻塞式的方法；苦苦的等待；
System.out.println("a client connet!");
						DataInputStream dis = new DataInputStream(s.getInputStream());
						//readUTF()是阻塞式的方法；这就会导致如果一个Client连接后不发信息，
						//就会无法进行下一次循环，他的Client就无法连上Server
						System.out.println(dis.readUTF());
						dis.close();
				}
				
		} 
}