package com.vic.excel;

public class Test {

	public static void main(String[] args) {
		String str = "19543";
		String s[] = str.split(",");
		for(int i = 0;i < s.length;i++){
			System.out.println(i+":"+s[i]);
		}
	}

}
