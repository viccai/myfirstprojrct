package com.vic;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class NettedBase64Demo {

	public static void main(String[] args) {
		String s = "HR;;wsc;;1467776404293";//初始数据
		
		//加密
		String  result1= (new BASE64Encoder()).encodeBuffer(s.getBytes());
        System.out.println("result1=====加密数据=========="+result1);

        //解密
        String str2 = "";
        byte result2[];
		try {
			result2 = (new BASE64Decoder()).decodeBuffer(result1);
			str2=new String(result2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println("str2========解密数据========"+str2);

	}

}
