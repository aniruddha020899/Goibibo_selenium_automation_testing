package goibibo_login;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.Base;
import framework.Excel;
import framework.KeyWords;

public class login extends Base{
	@Test(dataProvider = "dp")
	public void LoginTest(String no, String mobile) throws Exception {
		KeyWords key = new KeyWords(driver);
		Excel ex = new Excel(prop.getProperty("excelPath"));
		Scanner sc = new Scanner(System.in);

		try {
			key.getURL(prop.getProperty("url"));
			key.click("xpath", prop.getProperty("loginButton"));
			key.type("xpath", prop.getProperty("mobileField"), mobile);
			key.click("xpath", prop.getProperty("continueButton"));
			
			System.out.println("Enter the OTP:");
			String otp = sc.next();
			driver.findElement(By.xpath(prop.getProperty("otpField"))).sendKeys(otp);
			sc.close();

			WebDriverWait wt = new WebDriverWait(driver, 10);
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath(prop.getProperty("loginPage"))));
			String name = driver.findElement(By.xpath(prop.getProperty("loginPage")))
					.getText();
			Assert.assertEquals(name, "Hey");
			System.out.println("Login Successfull");
			
			ex.writeData("Sheet1", Integer.parseInt(no), 2, "passed");
			System.out.println(Integer.parseInt(no));
			OneWay w=new OneWay();
		} catch (Exception e) {
			System.out.println("Login Failed....Entered mobile number or OTP is invalid");
			
			ex.writeData("Sheet1", Integer.parseInt(no), 2, "failed");
			System.out.println(Integer.parseInt(no));
		}

	}

	@DataProvider
	public Object[][] dp() throws Exception {		

		Excel e = new Excel("C:\\Users\\aniru\\OneDrive\\Desktop\\Book7.xlsx");
		Object data[][] = new Object[4][2];
		for(int i=1; i<=4;i++ ) {
			data[i-1][0] = e.readData("Sheet1", i, 0);
			data[i-1][1] = e.readData("Sheet1", i, 1);
		}
		
		return data;
	}
}
