package framework;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class KeyWords {

	WebDriver driver;
	public KeyWords(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getURL(String url) {
		driver.get(url);
	}
	
	public void click(String locatorType, String locatorValue) {
		if(locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).click();
		} else if(locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).click();
		} else if(locatorType.equalsIgnoreCase("link")) {
			driver.findElement(By.linkText(locatorValue)).click();
		} else if(locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).click();
		} else if(locatorType.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(locatorValue)).click();
		}
		
	}
	
	public void type(String locatorType, String locatorValue, String data) {
		if(locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		} else if(locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locatorValue)).sendKeys(data);
		} else if(locatorType.equalsIgnoreCase("link")) {
			driver.findElement(By.linkText(locatorValue)).sendKeys(data);
		} else if(locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locatorValue)).sendKeys(data);
		} else if(locatorType.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(locatorValue)).sendKeys(data);
		}
	}
	
//	public void selectFromDropDown(String locatorType, String locatorValue, String option) {
//		if(locatorType.equalsIgnoreCase("id")) {
//			new Select (driver.findElement(By.id(locatorValue))).selectByVisibleText(option);
//		} else if(locatorType.equalsIgnoreCase("name")) {
//			new Select (driver.findElement(By.name(locatorValue))).selectByVisibleText(option);
//		} else if(locatorType.equalsIgnoreCase("link")) {
//			new Select (driver.findElement(By.linkText(locatorValue))).selectByVisibleText(option);
//		} else if(locatorType.equalsIgnoreCase("xpath")) {
//			new Select (driver.findElement(By.xpath(locatorValue))).selectByVisibleText(option);
//		} else if(locatorType.equalsIgnoreCase("css")) {
//			new Select (driver.findElement(By.cssSelector(locatorValue))).selectByVisibleText(option);
//		}
//
//	}
	
	public void getSnap(String name) throws Exception {
		TakesScreenshot tc = (TakesScreenshot) driver;
		File from = tc.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(from, new File(name + ".png"));
	}
}
