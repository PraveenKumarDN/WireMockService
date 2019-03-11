package com.kkpravee.framework.Listeners;

import com.kkpravee.framework.extentReports.FExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static com.kkpravee.framework.extentReports.FExtentTestManager.endTest;
import static com.kkpravee.framework.extentReports.FExtentTestManager.getResult;
import static com.kkpravee.framework.extentReports.FExtentTestManager.getTest;
import static com.kkpravee.framework.extentReports.FExtentTestManager.startTest;

public class FExtentReportListener extends TestListenerAdapter {

    static ExtentTest test ;
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("onStart method executed: " + iTestContext.getName());
        FExtentManager.getReporter();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart method executed: " +  getTestMethodName(iTestResult) + " start");
        //Start operation for extentreports.
        test = startTest(iTestResult.getMethod().getMethodName(),"");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("onTestSuccess method executed: " +  getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
        getTest().log(LogStatus.PASS, "Test passed");
        getResult(iTestResult, test);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("onTestFailure method executed: " +  getTestMethodName(iTestResult) + " failed");
        //Extentreports log and screenshot operations for failed tests.
         getTest().log(LogStatus.FAIL,"Test Failed");
         getResult(iTestResult, test);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("onTestSkipped method executed: "+  getTestMethodName(iTestResult) + " skipped");
        //Extentreports log operation for skipped tests.
        getTest().log(LogStatus.SKIP, "Test Skipped");
        getResult(iTestResult, test);
    }

   @Override
   public void onFinish(ITestContext iTestContext) {
       System.out.println("onFinish method executed: " + iTestContext.getName());
       //Do tier down operations for extentreports reporting!
       endTest();
   }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        getResult(iTestResult, test);
    }

}
