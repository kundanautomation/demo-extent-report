package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	 WebDriver driver;
	 
     public LoginPage(WebDriver driver){ 
             this.driver=driver; 
     }
             
     //Using FindBy for locating elements
     @FindBy(how=How.XPATH, using="//*[@name='email']//self::input") WebElement elUserName;
     @FindBy(how=How.XPATH, using="//*[@name='password']//self::input") WebElement elPassword;
     @FindBy(how=How.XPATH, using="//*[@id='m_login_signin_submit']//self::button") WebElement elLoginButton;
             
     
     public void sendEmail(String strUserName) {
    	 elUserName.clear();
    	 elUserName.sendKeys(strUserName);    	 
     }
     
     public void sendPassword(String strPassword) {
    	 elPassword.clear();
    	 elPassword.sendKeys(strPassword);
     }
     
     public void clickLoginButton() {    	 
    	 elLoginButton.click();
     }
     
}
