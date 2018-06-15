package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;


import org.testng.annotations.BeforeSuite;


import pages.LoginPage;

public class TestBase {
	public static WebDriver driver = null;
	
	
	
	 @BeforeSuite
	 public void initialize() throws IOException{
	 
	 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\java\\drivers\\chromedriver.exe");
	 driver = new ChromeDriver(); 
	 //To maximize browser
	                driver.manage().window().maximize();
	         //Implicit wait
	        	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	 
	            driver.get("http://ec2-18-197-112-141.eu-central-1.compute.amazonaws.com/#/login");
	            
	            //Login into application
	            adminLogin();
	            
	 }
	 
	 public WebDriver getDriver() {
		    return driver;
		}
	 
	 public void adminLogin() {
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 
		 String strUserName = "devtestemailid@gmail.com";
		 String strPassword = "admin@123";
		 loginPage.sendEmail(strUserName); // enter username
		 loginPage.sendPassword(strPassword); //enter password
		 loginPage.clickLoginButton();
		 try {		
			 WebDriverWait wait = new WebDriverWait(driver,30);
			 boolean isDashboard = wait.until(ExpectedConditions.urlContains("dashboard"));			 
			 assertEquals(isDashboard, true);			 
			 
		 }catch (Exception e) {			 
			 System.out.println("Test case fail due to: "+e);
		 }
		 
		 
	 }
	 @AfterSuite
	 //Test cleanup
	 public void TeardownTest()
	    {
	        TestBase.driver.quit();
	    }
	 
	
}
