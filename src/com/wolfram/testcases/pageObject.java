/*By a Wolfram Software Engineer in Test Candidate ---Linghe Zheng*/
package com.wolfram.testcases;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageObject{
	//Elements 
	@FindBy (name="query")
	public WebElement query;
	@FindBy (name="equal")
	public WebElement equalButton;
	@FindBy (xpath="//*[@id='Input']/section/div[2]/div/p")	
	public WebElement openCode;	
	@FindBy (xpath="//*[@id='notebookContent']/div/div[3]/div/div[7]/div/div[2]/div/div/div/div[4]/div/div[1]/div/div/div/div[3]/div/pre/div/div/div/div/div/div/div/div/div/div/table/tbody/tr[1]/td[2]/div")	
	public WebElement runCode;
	@FindBy (xpath="//*[@id='notebookContent']/div/div[3]/div/div[10]/div/div[2]/div/div/div/div[4]/div/div[1]/div/div/div/div[3]/div/pre/div/div/div/div/div/div/div/div/div/div/table/tbody/tr[1]/td[2]/div")	
	public WebElement runNumberName;
	@FindBy (xpath="//*[@id='mathSidebar']/div/ul/li[1]/a")
	public WebElement linkIntro;
	@FindBy (xpath="//*[@id='Result']/section/div[1]/div/img")
	public WebElement result;
	@FindBy (xpath="//*[@id='Input']/section/div[1]/div/img")
	public WebElement input;
	//WebDriver
	public WebDriver driver;
	//Method
	public pageObject(){
		this.driver=new ChromeDriver();
		PageFactory.initElements(driver, this);
	}
	public void load(String url){
		driver.get(url);
	}
	public void quit(){
		driver.quit();
	}
	public void close(){
		driver.close();
	}
	public String getURL(){
		return driver.getCurrentUrl();
	}
	//getWindow and switchBack are a pair

	public String getWindow(){
		return driver.getWindowHandle();
	}
	public void switchNext() throws InterruptedException{	
		String current_handle = driver.getWindowHandle();
		Set<String> all_h = driver.getWindowHandles();
		Iterator<String> it = all_h.iterator();
		while(it.hasNext()){			
			if(it.next() != current_handle){
				driver.switchTo().window(it.next());
				break;
			}
			it.next();
		}		
	}
	public void switchWindow(String current_handle){
		driver.switchTo().window(current_handle);
	}
	public void waitClickable(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitVisible(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}	 
}

