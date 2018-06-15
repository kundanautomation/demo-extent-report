package testsDefineSection;

import tests.TestBase;
import utility.ExcelUtils;

import static org.testng.Assert.assertEquals;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import extentReport.ExtentTestManager;
import pages.AddAssetTypePage;
import pages.HomePage;

public class AddAssetTypeTest extends TestBase{	 
	

	@BeforeMethod
	public void beforeMethod() {
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
	}
	
	@BeforeTest
	public void openAdminDefineSection() {
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.openAdminSection();
        
	}
	
	
	@Test(priority=1)
	public void testAllFieldsRequired() {
		AddAssetTypePage addAssetTypePage = PageFactory.initElements(driver, AddAssetTypePage.class);						
		addAssetTypePage.elFormAssetType.clear();
		addAssetTypePage.elFormAssetDescription.clear();
		addAssetTypePage.elAddButton.click();
		
		int totalError = addAssetTypePage.elErrorCount.size();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case = Check all fields are required");
		assertEquals(totalError, 2);

	}
	
	@Test(dataProvider="AssetType", priority=2)
	public void testAddAssetType(String assetType, String assetDescription, String expectedResult, String dataToRun, String testInfo) throws IOException {
       
		if(dataToRun.equals("N")){
			ExtentTestManager.getTest().log(LogStatus.SKIP, "SKIP test case");
			throw new SkipException("Skipping - This is not ready for testing ");
		}
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case= "+testInfo);		
		AddAssetTypePage addAssetTypePage = PageFactory.initElements(driver, AddAssetTypePage.class);						
		addAssetTypePage.sendAssetType(assetType);
		addAssetTypePage.sendAssetDescription(assetDescription);
		addAssetTypePage.clickOnAddButton();

		//test.log(LogStatus.INFO, "the test tours is passed");
		try {
				//Verify popup presence
				addAssetTypePage.elementPopUpDisplay();			
				
				//Get popup message
				String msg = addAssetTypePage.getMessage();
				addAssetTypePage.clickOnOkButton();
				
				//Verify popup message as per testcase
				assertEquals(msg, expectedResult);
				
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Popup not appear & "+e);			
			throw(e);
		}		
	}
	
	@DataProvider(name="AssetType")	 
    public Object[][] AssetType() throws Exception{		
		String sTestCaseName;
		int iTestCaseRow;
		 // Setting up the Test Data Excel file		 
	 	ExcelUtils.setExcelFile("D://projects//automation//BenDavid//benDavid//testData.xls","AssetTypeAddData"); 
	 	sTestCaseName = this.toString();  
	  	sTestCaseName = ExcelUtils.getTestCaseName(this.toString());	  	
	  	System.out.println("Test Case ="+sTestCaseName);  
	 	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);		
         Object[][] testObjArray = ExcelUtils.getTableArray("D://projects//automation//BenDavid//benDavid//testData.xls","AssetTypeAddData",iTestCaseRow); 
         return (testObjArray);         
		}
	
	//Test Add Assert Filter
	@Test(dataProvider="AssetTypeFilter", priority=3)
	public void testAddAssetFilter(String assetType, String assetDescription, String expectedResult, String dataToRun, String testInfo) throws InterruptedException {
		
		if(dataToRun.equals("N")){
			ExtentTestManager.getTest().log(LogStatus.SKIP, "SKIP test case");
			throw new SkipException("Skipping - This is not ready for testing ");
		}
		
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case= "+testInfo);
		
		AddAssetTypePage addAssetTypePage = PageFactory.initElements(driver, AddAssetTypePage.class);						
		addAssetTypePage.openFilterBox();
		String[] ss=assetType.split(",");
		for(int i=0;i<ss.length;i++)
		{
			addAssetTypePage.sendAssetTypeFilter(ss[i]);
		}
		
		addAssetTypePage.sendAssetDescriptionFilter(assetDescription);
		addAssetTypePage.clickOnFilterSearchButton();
		
		//Verify Expected total item text
		try {			
			addAssetTypePage.TotalItem(expectedResult);
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,e);
			throw(e);
		}
		
		String firstRowData = addAssetTypePage.getFirstRowData();
		System.out.println("First Row ="+firstRowData);
		String actualResult;
		if(firstRowData.endsWith("No data available in table")) {
			 actualResult = "Total Items: 0";
		}else {
			System.out.println("Total Item ="+addAssetTypePage.getTableRow());
			 actualResult = "Total Items: "+addAssetTypePage.getTableRow();
		}
		
		try {
			assertEquals(actualResult, expectedResult);
		}finally {
			driver.navigate().refresh();
		}		
	}
	
	@DataProvider(name="AssetTypeFilter")	 
    public Object[][] AssetTypFilter() throws Exception{		
		String sTestCaseName;
		int iTestCaseRow;
		 // Setting up the Test Data Excel file		 
	 	ExcelUtils.setExcelFile(".//testData.xls","AssetTypeFilterData"); 
	 	sTestCaseName = this.toString();  
	  	sTestCaseName = ExcelUtils.getTestCaseName(this.toString());	  	
	  	System.out.println("Test Case ="+sTestCaseName);  
	 	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,0);		
         Object[][] testObjArray = ExcelUtils.getTableArray(".//testData.xls","AssetTypeFilterData",iTestCaseRow); 
         return (testObjArray);         
		}
	
}
