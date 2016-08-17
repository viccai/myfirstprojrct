package com.vic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static void main(String[] args) {
		MD5Util mu = new MD5Util();
		String str = "123456";
		String str1 = str;
		String str2 = str;
		String estr = mu.encode(str);
		String estr1 = mu.encode(str);
		System.out.println("加密前：" + str);
		System.out.println("加密后：" + estr);
		System.out.println("加密后：" + estr1);
		if(str1==str2){
			System.out.println("1");
		}
		if(estr.endsWith(estr1)){
			System.out.println("2");
		}
	}

	public String encode(String s) {
		String returnStr = "";
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			returnStr = new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

}
