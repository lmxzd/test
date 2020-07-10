package com.company.java.excise;

import java.io.IOException;
import java.net.ServerSocket;

public class Tomcat {
	//通过入口对象,一步一步的抽取相应的对象
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			System.out.println("服务器已启动");
			server.accept();//阻塞式方法
			System.out.println("客户端已连接");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
