package webautomation.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import webautomation.resources.ExtentReporterNG;

import java.io.IOException;

// call this from testng.xml

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    //Concurrency problem - Implement ThreadLocal class to avoid sync issues in Tests
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    ExtentReports extent = ExtentReporterNG.getReportObject();
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // assigns unique thread id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //test.log(Status.PASS,"Test passed");
        extentTest.get().log(Status.PASS,"Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
       //Take Screenshot when test failed
        extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
     extent.flush();
    }
}
