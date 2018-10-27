/*By a Wolfram Software Engineer in Test Candidate ---Linghe Zheng*/
package com.wolfram.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAnnotation  {
	//pre-conditions
	@BeforeSuite
	public void SetUp(){
		System.out.println("Set Up System Property for Chrome");
	}
	@BeforeClass
	public void launchBrowser(){
		System.out.println("Launch Chrome Browser");
	}
	@BeforeMethod
	public void enterURL(){
		System.out.println("enter URL");
	}
	@BeforeTest
	public void login(){
		System.out.println("Login to Website");
	}
	//test cases
	@Test
	public void googleTitleTest(){
		System.out.println("Wolfram Title Test");	
	}
	//post-conditions
	@AfterMethod
	public void logOut(){
		System.out.println("Logout from Website");
	}
	@AfterTest
	public void deleteAllCookies(){
		System.out.println("Delete all Cookies");
	}
	@AfterClass
	public void CloseTheBroswer(){
		System.out.println("Close Browser");
	}
	@AfterSuite
	public void GenerateTestReport(){
		System.out.println("Generate Test Report");
	}
}
