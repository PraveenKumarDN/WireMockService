package com.kkpravee.framework.extentReports;

import com.relevantcodes.extentreports.ExtentReports;;

import java.io.File;

/**
 * ExtentReports extent instance created here.
 * @author praveenkumar
 */
public class FExtentManager {

    //To set the environmental info in report
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String projectClassPath = System.getProperty("user.dir");
            File testOutput = new File(projectClassPath + "/target/test-output");
            testOutput.mkdir();
            extent = new ExtentReports(projectClassPath+"/target/test-output/MyCustomExtentReport.html", true);
            extent.addSystemInfo("Host Name", "FCGRN-L0119");
            extent.addSystemInfo("Environment", "QA Staging");
            extent.addSystemInfo("User Name", "Praveen Kumar DN");
            extent.loadConfig(new File(projectClassPath+"/extent-config.xml"));
        }
        return extent;
    }
}
