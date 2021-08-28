package goibibo_login;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import framework.Base;
import framework.Excel;
import framework.KeyWords;
public class RoundWay extends Base{	//declaration of the class that extends the base class
	@Test(dataProvider = "dp1")		// data provider to access the excel sheet
	public void LoginTest(String no, String mobile) throws Exception {
		KeyWords key = new KeyWords(driver);	//creating object of keyword framework
		Excel ex = new Excel("C:\\Users\\aniru\\OneDrive\\Desktop\\login.xlsx");  //creating object of Excel framework
		WebDriverWait wt = new WebDriverWait(driver, 10);	//creating object of WebDriverWait framework
		Scanner sc = new Scanner(System.in);

		try {
			key.getURL(prop.getProperty("url"));		//redirecting to the url
			key.click("xpath", prop.getProperty("loginButton"));	//Clicking on login Button
			key.type("xpath", prop.getProperty("mobileField"), mobile);	//Typing the mobile number
			key.click("xpath", prop.getProperty("continueButton"));	//Clicking on continue

			System.out.println("Enter the OTP:");
			String otp = sc.next();					//Entering The OTP through console
			driver.findElement(By.xpath(prop.getProperty("otpField"))).sendKeys(otp);	//sends the OTP from Console to url for confirming mobile number
			sc.close();

			
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("loginPage"))));		//Using Explicit Wait
			String name = driver.findElement(By.xpath(prop.getProperty("loginPage"))).getText();
			Assert.assertEquals(name, "Hey");	//Using assert for checking actual with expected result
			System.out.println("Login Successfull");

			ex.writeData1("Sheet1", Integer.parseInt(no), 2, "passed");			//for writing into excel sheet
			ex.writeData1("Sheet1", Integer.parseInt(no), 3, "No errors");		//for writing into excel sheet
			test= reports.startTest("Login Module");							//For creating ExtentReport
			test.log(LogStatus.PASS, "Valid Phone Number");						//For creating ExtentReport
			
		} catch (Exception e) {
			System.out.println("Login Failed....Entered mobile number or OTP is invalid");
			//String LoginErrMsg = driver.findElement(By.xpath(prop.getProperty("LoginErrMsg"))).getText();

			ex.writeData1("Sheet1", Integer.parseInt(no), 2, "failed");			//for writing into excel sheet
			ex.writeData1( "Sheet1", Integer.parseInt(no), 3, "Please enter a 10 digit mobile number");		//for writing into excel sheet
			test= reports.startTest("Login Module");							//For creating ExtentReport
			test.log(LogStatus.PASS, "Error: "+"Please enter a 10 digit mobile number");			//For creating ExtentReport
		}

	}
	@Test(dataProvider = "dp")		// data provider to access the excel sheet
  public void round(String no,String source, String dest, String title, String first, String last, String email, String mob) throws Exception {
	  KeyWords key = new KeyWords(driver);	//creating object of keyword framework
	  JavascriptExecutor js=(JavascriptExecutor)driver;	// using js executor for scrolling the web page
	  Excel ex = new Excel(prop.getProperty("excelPath1")); //creating object of excel framework
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 	//using implicit wait - between every two operation
	  
	  try	//using for error handling
	  {
	  key.getURL(prop.getProperty("url"));	//redirecting to the url
	  driver.manage().window().maximize();	//to maximize the web page
	  
	  Actions ac = new Actions(driver);	//using Actions class for keyboard events
	  WebElement search = driver.findElement(By.id("gosuggest_inputSrc"));	//source input
	   ac.sendKeys(search, source).build().perform();	// the source will be accessed from excel sheet
	  Thread.sleep(2000);
	   ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); //arrow down for selecting the source loc

	   Thread.sleep(5000);
	  ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); //arrow down for selecting the source loc
	  
	  WebElement search1 = driver.findElement(By.id("gosuggest_inputDest"));	//dest input
	   ac.sendKeys(search1, dest).build().perform();	// the dest will be accessed from excel sheet
	  Thread.sleep(2000);
	   ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();	//arrow down for selecting the dest loc

	   Thread.sleep(5000);
	  ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); 	////arrow down for selecting the dest loc
			  
					  
	  key.click("xpath", prop.getProperty("departure")); 	//getting departure details
	  key.click("xpath", prop.getProperty("date"));		//getting departure date details
	  key.click("xpath", prop.getProperty("return"));	//getting return date details
	  key.click("xpath", prop.getProperty("date1"));	//getting return date details
	  key.click("xpath", prop.getProperty("searchButton"));	//clicking on search button
	  Thread.sleep(5000);
	  key.click("xpath", prop.getProperty("bookFlightButton"));	//booking the flight
	  Thread.sleep(5000);
	  
	  WebElement E;	//declaring the web element for scroll
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");	//scrolling down
	  Thread.sleep(2000);
	  E=driver.findElement(By.xpath(prop.getProperty("select"))); 	// clicking on the title text box 
	  Select a=new Select(E); 	//selecting from dropdown
	 


	  a.selectByIndex(Integer.parseInt(title)); //getting the title by index
	  key.type("name",prop.getProperty("firstName"),first); 	//getting the first name from excel
	  key.type("name",prop.getProperty("lastName"),last);		//getting the last name from excel
	  key.type("name",prop.getProperty("email"),email);			//getting the email from excel
	  key.type("name",prop.getProperty("mobileNumber"),mob);	//getting the mobile number from excel
	  key.click("xpath",prop.getProperty("proceed"));			//click on proceed
	  Thread.sleep(2000);
	  key.click("xpath",prop.getProperty("insure"));			//click on insurance part
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");	//scroll down
	  key.click("xpath",prop.getProperty("proceed"));				//click on proceed
	  Thread.sleep(2000);
	  key.click("xpath",prop.getProperty("ok"));		//click on ok
	  Thread.sleep(2000);
	  key.click("xpath",prop.getProperty("proceed"));	//click on proceed
	  Thread.sleep(5000);
	  String name = driver.findElement(By.xpath(prop.getProperty("ticket"))) //the successful passenger info
				.getText();
	  Assert.assertEquals(name, "TICKET DETAILS");	//checking expected results with actual results
		System.out.println("Entered Details are correct");		//print TICKET DETAILS in console when conditions are true
		ex.writeData("Sheet1", Integer.parseInt(no), 8, "passed"); //Writing the output into excel
		System.out.println(Integer.parseInt(no));	
		test= reports.startTest("Verify UserDetails module");
		test.log(LogStatus.PASS, "Valid User details");
	  }
	  
	  catch(Exception e)	//catch block
	  {
		  String error = driver.findElement(By.xpath(prop.getProperty("errormsg"))).getText(); //error message gets recorded
		  System.out.println("Invalid details"); //prints invalid details in console
		  ex.writeData("Sheet1", Integer.parseInt(no), 8, "failed"); //writing output into excel sheet
		  ex.writeData("Sheet1", Integer.parseInt(no), 9, error);		//specifying the error in excel sheet
			System.out.println(Integer.parseInt(no));
			test= reports.startTest("Verify UserDetails module");
			test.log(LogStatus.PASS, "Error: "+error);
	  }
  }
	@DataProvider	//defining the data provider
	public Object[][] dp1() throws Exception {		

		Excel e = new Excel("C:\\Users\\aniru\\OneDrive\\Desktop\\login.xlsx");	//creating an object for excel sheet being used
		Object data[][] = new Object[3][2];		//array declaration
		for(int i=1; i<=3;i++)		//for loop for rows
		{	//reading data from excel
			data[i-1][0] = e.readData("Sheet1", i, 0);
			data[i-1][1] = e.readData("Sheet1", i, 1);
			
		}
		
		return data;	//return statement
	}

	@DataProvider	//defining the data provider
	public Object[][] dp() throws Exception {		

		Excel e = new Excel("C:\\Users\\aniru\\OneDrive\\Desktop\\sample1.xlsx");	//creating an object for excel sheet being used
		Object data[][] = new Object[5][8];		//array declaration
		for(int i=1; i<=5;i++)		//for loop for rows
		{	//reading data from excel
			data[i-1][0] = e.readData("Sheet1", i, 0);
			data[i-1][1] = e.readData("Sheet1", i, 1);
			data[i-1][2] = e.readData("Sheet1", i, 2);
			data[i-1][3] = e.readData("Sheet1", i, 3);
			data[i-1][4] = e.readData("Sheet1", i, 4);
			data[i-1][5] = e.readData("Sheet1", i, 5);
			data[i-1][6] = e.readData("Sheet1", i, 6);
			data[i-1][7] = e.readData("Sheet1", i, 7);
		}
		
		return data;	//return statement
	}
}