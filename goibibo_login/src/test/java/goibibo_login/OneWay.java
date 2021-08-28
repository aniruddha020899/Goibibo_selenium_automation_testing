package goibibo_login;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import framework.Base;
import framework.KeyWords;

public class OneWay extends Base{
  @Test
  public void f() throws Exception {
	  KeyWords key = new KeyWords(driver);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //driver.get("https://www.goibibo.com/flights/");
	  key.getURL(prop.getProperty("url"));
	  driver.manage().window().maximize();
	  
	  Actions ac = new Actions(driver);
	  WebElement search = driver.findElement(By.id("gosuggest_inputSrc"));
	   ac.sendKeys(search, "mum").build().perform();
	  Thread.sleep(2000);
	   ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

	   Thread.sleep(5000);
	  ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); 
	  
	  WebElement search1 = driver.findElement(By.id("gosuggest_inputDest"));
	   ac.sendKeys(search1, "del").build().perform();
	  Thread.sleep(2000);
	   ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

	   Thread.sleep(5000);
	  ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); 
			  
					  
	  key.click("xpath", prop.getProperty("departure"));
	  key.click("xpath", prop.getProperty("date"));
	  key.click("xpath", prop.getProperty("return"));
	  key.click("xpath", prop.getProperty("date1"));
	  key.click("xpath", prop.getProperty("searchButton"));
	  Thread.sleep(5000);
	  
	  
  }
}
