package com.kkpravee.framework.extentReports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

        /* OB: extentTestMap holds the information of thread ids and ExtentTest instances.
                ExtentReports instance created by calling getReporter() method from ExtentManager.
                At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
                At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
                At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
         */

public class FExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = FExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static void getResult(ITestResult iTestResult, ExtentTest extentTest) {
        try {
            if (iTestResult.getStatus() == ITestResult.FAILURE) {
                extentTest.log(LogStatus.FAIL, iTestResult.getThrowable());
            } else if (iTestResult.getStatus() == ITestResult.SKIP) {
                extentTest.log(LogStatus.SKIP, iTestResult.getThrowable());
            }
            extent.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}