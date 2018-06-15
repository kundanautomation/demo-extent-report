package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
 WebDriver driver;
	 
     public HomePage(WebDriver driver){ 
             this.driver=driver; 
     }
     
   //Using FindBy for locating elements
     //Left navigation Admin->Define
     @FindBy(how=How.XPATH, using="//*[@class='m-menu__link' and @title='Define']//self::a") WebElement elDefineLink;
     @FindBy(how=How.XPATH, using="//*[@href='#/admin/define']//self::a[1]") WebElement elAdminLink;
   
     
     public void openDefectSection() {
    	 elDefineLink.click();
     }
     
     public void openAdminSection() {
    	 elAdminLink.click();
     }
     
}
