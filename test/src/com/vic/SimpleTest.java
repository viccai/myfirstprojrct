package com.vic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

public class SimpleTest extends TestCase {
	
	private Singleton myNumber;
	
	@BeforeClass
	public void testBeforeClass(){
		System.out.println("BeforeClass");
	}
	
	@AfterClass
	public void testAfterClass(){
		System.out.println("AfterClass");
	}
	
	@Before
	public void testBefore(){
		System.out.println("Before");
	}
	
	@After
	public void testAfter(){
		System.out.println("After");
	}
	
	@Test(timeout = 20)
	public void testTimeOut(){
		System.out.println("TimeOut");
		System.out.println("testAdd method");
		Singleton s = myNumber.getSingeleton();
		//assertEquals(5, result);
	}
	
	@Ignore
	public void testIgnore(){
		System.out.println("Ignore");
	}
	
}
