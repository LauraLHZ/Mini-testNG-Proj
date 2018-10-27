/*By a Wolfram Software Engineer in Test Candidate ---Linghe Zheng*/
package com.wolfram.testcases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class wolframAlphaTest{
	pageObject wolframalpha;
	String Url;
	String[] args;	
	@BeforeTest
	public void setPage(){		
		Url="http://www.wolframalpha.com/";
	}
	@BeforeMethod
	public void loadPage(){	
		wolframalpha=new pageObject();
		wolframalpha.load(Url);
	}
	//Test the flow from part1 && Verify URL
	@Test
	public void testQuery() throws InterruptedException {				
		//send 2+2
		wolframalpha.query.sendKeys("2+2");
		wolframalpha.equalButton.click();
		//Open code 
		wolframalpha.waitClickable(wolframalpha.openCode);
		wolframalpha.openCode.click();
		//Run Code
		wolframalpha.waitClickable(wolframalpha.runCode);
		wolframalpha.runCode.click();
		//Run Number Name
		wolframalpha.waitClickable(wolframalpha.runNumberName);
		wolframalpha.runNumberName.click();	
		//--------Verify One Link on the Right-------
		//Verify "Fast Introduction to the Wolfram Language for Math Students"
		String linkIntroHref=wolframalpha.linkIntro.getAttribute("href");
		//System.out.print(linkIntroHref+"\n");
		wolframalpha.linkIntro.click();
		//get base window 
		String base_handle=wolframalpha.getWindow();
		//Switch to next window
		wolframalpha.switchNext();
		//System.out.println(("hello "+wolframalpha.getURL()));
		String link1=wolframalpha.getURL();
		if (!link1.contains(linkIntroHref))
			System.out.print("Intro link error.\n");
		wolframalpha.close();
		//Switch back to base window
		wolframalpha.switchWindow(base_handle);		
	}
	//-------Unit testing for simple math formula && Read data from a csv file------
	@DataProvider(name="testInputData")
	public Object[][] data() throws Exception
	{
		return getData("/Users/lzheng/Desktop/ClassNote/Selenium/Wolfram/src/com/wolfram/testcases/testData.csv");
	}
	@Test (dataProvider = "testInputData")
	public void testInput(String input, String result) {
		wolframalpha.query.sendKeys(input);
		wolframalpha.equalButton.click();
		wolframalpha.waitVisible(wolframalpha.input);	
		wolframalpha.waitVisible(wolframalpha.result);
		String outputShow=wolframalpha.result.getAttribute("alt");
		assert outputShow.equals(result);
	}
	@AfterMethod
	public void closeWindow(){
		wolframalpha.close();
	}
	@AfterTest
	public void quitDriver(){
		wolframalpha.quit();
	}
	 public static Object[][] getData(String filePath) throws Exception {
        String line;
        List<String[]> list = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));     
        //Read the first line (Title)
        reader.readLine();
        //Read Data
        while ((line = reader.readLine()) != null) {
        	//Read every line
            String[] fileds = line.split(","); 
            list.add(fileds);
        }
        reader.close();
        Object[][] result = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i); //Store line and column 
        }
        return result;
	 }
}
