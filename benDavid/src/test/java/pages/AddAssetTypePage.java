package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class AddAssetTypePage {

	WebDriver driver;
		 
    public AddAssetTypePage(WebDriver driver){ 
            this.driver=driver; 
    }
    
    //Using FindBy for locating elements
    @FindBy(how=How.XPATH, using="//*[@name='form']//following::input[1]") public WebElement elFormAssetType;
    @FindBy(how=How.XPATH, using="//*[@name='form']//following::input[2]") public WebElement elFormAssetDescription;
    @FindBy(how=How.XPATH, using="//*[@class='btn btn-primary' and text()='Add ']//self::button") public WebElement elAddButton;
    @FindBy(how=How.XPATH, using="//*[text()='OK']//ancestor::button") WebElement elOkButton;
    @FindBy(how=How.XPATH, using="//*[@class='la la-angle-up']//self::i") WebElement elOpenFilterDiv;
    @FindBy(how=How.XPATH, using="//*[@class='item-text']//self::span") WebElement elTotalItemText;
    @FindBy(how=How.XPATH, using="//*[@id='DataTables_Table_0']/tbody/tr/td[1]") private List<WebElement> elTableRow;    
    @FindBy(how=How.XPATH, using="//*[text()='Asset Type List']//following::input[1]") WebElement elFilterAssetType;
    @FindBy(how=How.XPATH, using="//*[text()='Asset Type List']//following::input[2]") WebElement elFilterAssetDescription;
    @FindBy(how=How.XPATH, using="//*[text()='Search' and @class='btn btn-primary btn-sm']//self::a") WebElement elFilterSearchButton;    
    @FindBy(how=How.XPATH, using="//*[@id='swal3-content' or @id='swal2-content']//self::div") WebElement elPopUpMessage; 
    @FindBy(how=How.XPATH, using="//*[contains(text(),'This field is required')]//self::span") public List<WebElement> elErrorCount;
    
    public void TotalItem(String expectedTotalItems) {
    	WebDriverWait wait = new WebDriverWait(driver,20);    	
    	wait.until(ExpectedConditions.textToBePresentInElement(elTotalItemText, expectedTotalItems));    	
    }

    public String getTotalItemText() {
    	return elTotalItemText.getText();    	
    }
    
    public void clickOnFilterSearchButton() {
    	elFilterSearchButton.click();
    }
    
    public void sendAssetTypeFilter(String strAssetType) throws InterruptedException{
    	elFilterAssetType.sendKeys(strAssetType,Keys.ENTER);
    	Thread.sleep(1000);
    }
    
    public void sendAssetDescriptionFilter(String strAssetDescription) {
    	elFilterAssetDescription.clear();
    	elFilterAssetDescription.sendKeys(strAssetDescription);		
    }
    
    public int getTableRow() {
        return elTableRow.size();
    }
    
    public void openFilterBox() {    	
    	elOpenFilterDiv.click();
    }
    
    public String getFirstRowData() {
    	    	
    	return elTableRow.get(0).getText();
    }
    
    
    public void sendAssetType(String strAssetType) {
    	elFormAssetType.clear();
    	elFormAssetType.sendKeys(strAssetType);
    }
    
    public void sendAssetDescription(String strAssetDescription) {
    	elFormAssetDescription.clear();
    	elFormAssetDescription.sendKeys(String.valueOf(strAssetDescription));
    }
    
    public void clickOnAddButton() {
    	elAddButton.click();
    }
    
    public void clickOnOkButton() {
    	elOkButton.click();
    }
    
    //verify success/error message popup appear or not
    public void elementPopUpDisplay() {    	
    	
    	WebDriverWait wait = new WebDriverWait(driver,20);
    	wait.until(ExpectedConditions.visibilityOf(elPopUpMessage));    	    			
    }
    
    public String getMessage() {
    		String getMsg = elPopUpMessage.getText();
    		return getMsg;
    }
    
}
