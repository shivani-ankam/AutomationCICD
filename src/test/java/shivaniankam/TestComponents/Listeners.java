package shivaniankam.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import shivaniankam.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{//its one of the interface provided by TestNG 
	
	//this will execute before any test executes
	ExtentTest test;
	WebDriver driver;
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// THread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidationTest)->test
		
	}
	
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		//ScreenShot and attach to report
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
