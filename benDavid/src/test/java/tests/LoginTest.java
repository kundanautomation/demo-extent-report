package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest extends TestBase {

	
	@Test(enabled=false)
	public void testLoginAdmin() {
		
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 
		 String strUserName = "devtestemailid@gmail.com";
		 String strPassword = "admin@123";
		 loginPage.sendEmail(strUserName); // enter username
		 loginPage.sendPassword(strPassword); //enter password
		 loginPage.clickLoginButton();
		 
		 
		 try {		
			 WebDriverWait wait = new WebDriverWait(driver,30);
			 boolean isDashboard = wait.until(ExpectedConditions.urlContains("dashboard"));
			 System.out.println("is deshboard: "+isDashboard);
			 assertEquals(isDashboard, true);			 
			 
		 }catch (Exception e) {			 
			 System.out.println("Test case fail due to: "+e);
		 }
		 
	}
}
