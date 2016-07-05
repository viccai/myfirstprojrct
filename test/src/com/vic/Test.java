package com.vic;

import java.io.Console;
import java.io.PrintWriter;

public class Test {

	public static void main(String[] args) {
		/*Singleton s1 = Singleton.getSingeleton();
		Singleton s2 = Singleton.getSingeleton();
		if(s1==s2){
			System.out.println("这是同一个实例");
		}else{
			System.out.println("这不是同一个实例");
		}*/
		
		/*Console c = System.console();
		System.out.println(c);
		if(c != null){
			PrintWriter printWriter = c.writer();
			printWriter.write("input:");
			c.flush();
			String str = c.readLine();
			c.format("%s", str);
		}*/
		
		String s = "1463985506414.mp4";
		String[] str = s.split("\\.");
		System.out.println(str[0]);
		
	}

}
