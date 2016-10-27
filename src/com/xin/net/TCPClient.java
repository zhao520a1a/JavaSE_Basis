package com.xin.net;

import java.net.*;
import java.io.*;

public class TCPClient {
		
		public static void main(String[] args) throws Exception {
				//表明向谁那个端口发出连接的请求，注：TCPClient中发消息的端口是系统自动随机分配的，不用我们指定；
				Socket s = new Socket("127.0.0.1", 8888);
				
				DataOutputStream  dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF("Hello TCPServer!");
				dos.flush();
				dos.close();
				s.close();
		}
}