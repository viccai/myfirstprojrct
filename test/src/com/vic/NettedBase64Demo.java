package com.vic;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class NettedBase64Demo {

	public static void main(String[] args) {
		String s = "HR;;wsc;;1467776404293";//��ʼ����
		
		//����
		String  result1= (new BASE64Encoder()).encodeBuffer(s.getBytes());
        System.out.println("result1=====��������=========="+result1);

        //����
        String str2 = "";
        byte result2[];
		try {
			result2 = (new BASE64Decoder()).decodeBuffer(result1);
			str2=new String(result2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println("str2========��������========"+str2);

	}

}
