package framework;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Base {
	public WebDriver driver;
	public Properties prop;
	public ExtentReports reports;
	public ExtentTest test;

	@BeforeMethod
	public void beforeMethod() throws Exception {
		reports = new ExtentReports(System.getProperty("user.dir") + "/test-output/LoginAndUserDetailReport.html", false);
//		test = reports.startTest("searchDemo");
		reports.addSystemInfo("Host Name", "SoftwareTesting").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Aniruddha Sawant");

		reports.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

		prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\aniru\\eclipse-workplace1\\goibibo_login\\src\\test\\resources\\files\\settings.property"));
		System.setProperty(prop.getProperty("driver"), prop.getProperty("driverPath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() throws Exception {

		reports.endTest(test);
		reports.flush();
		reports.close();
		driver.quit();


	}

	@AfterTest
	public void afterClass() {
	}

}