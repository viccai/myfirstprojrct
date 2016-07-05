package com.vic;

public class Singleton {
	private Singleton(){
		
	}
	
	private static Singleton s = new Singleton();
	
	public static Singleton getSingeleton(){
		return s;
	}
}
