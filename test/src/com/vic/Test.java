package com.vic;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import org.castor.util.Base64Decoder;
import org.castor.util.Base64Encoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


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
		
		/*String s = "1463985506414.mp4";
		String[] str = s.split("\\.");
		System.out.println(str[0]);*/
		
		String s = "HR;;wsc;;1467776404293";
		//Base64.encode(binaryData)
		/*String ss = (new Base64Encoder()).encode(s.getBytes());
		System.out.println(ss);
		//String str = "[C@55e859c0";
		byte result2[] = (new Base64Decoder()).decode(ss);
		String sss = new String(result2);
		System.out.println(sss);*/
		
		
		//char[]  result1= Base64Encoder.encode(s.getBytes());
		String  result1= (new BASE64Encoder()).encodeBuffer(s.getBytes());
		//(new BASE64Encoder()).encodeBuffer(key)
        System.out.println("result1=====加密数据=========="+result1);

        String str2 = "";
        byte result2[];
		try {
			result2 = (new BASE64Decoder()).decodeBuffer(result1);
			str2=new String(result2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("str2========解密数据========"+str2);
		
	}

}
