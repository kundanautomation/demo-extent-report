package extentReport;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import com.relevantcodes.extentreports.LogStatus;
import tests.TestBase;


public class TestListener extends TestBase implements ITestListener {
	
	 private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
	    
	    //Before starting all tests, below method runs.
	    @Override
	    public void onStart(ITestContext iTestContext) {
	        System.out.println("I am in onStart method " + iTestContext.getName());
	        iTestContext.setAttribute("WebDriver", this.driver);
	    }
	 
	    //After ending all tests, below method runs.
	    @Override
	    public void onFinish(ITestContext iTestContext) {
	        System.out.println("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for extentreports reporting!
	        ExtentTestManager.endTest();
	        ExtentManager.getReporter().flush();
	    }
	 
	    @Override
	    public void onTestStart(ITestResult iTestResult) {
	        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
	        //Start operation for extentreports.
	        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
	        //Extentreports log operation for passed tests.
	        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	    }
	 
	    @Override
	    public void onTestFailure(ITestResult iTestResult) {
	    Object testClass = iTestResult.getInstance();
	    WebDriver webDriver = ((TestBase) testClass).getDriver();
	    String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
	    ExtentTestManager.getTest().log(LogStatus.FAIL,iTestResult.getThrowable().getMessage(), ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	    ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
	    }
	 
	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
	        //Extentreports log operation for skipped tests.
	        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	    }
	 
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	    }
}