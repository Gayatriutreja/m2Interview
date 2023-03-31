package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class Base {
    public static WebDriver driver;
    public ConfigReader configRead;
    public ExcelReader excelRead;
    public static ExtentReports reports;
    public static ExtentTest extlogger;
    @BeforeSuite
    public void setupsuite()
    {
         excelRead =new ExcelReader();
        configRead=new ConfigReader();
        ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/"+BusinessReusable.currentdate()+"Result.html"));
        reports=new ExtentReports();
        reports.attachReporter(extent);
        extlogger=reports.createTest("Execution started");

    }
    @BeforeClass
    public void setup()
    {
        driver= BrowserFactory.startApplication(driver,configRead.getBrowser(),configRead.getUrl());

    }
    @AfterClass
    public void tearDown()
    {
        BrowserFactory.quitBrowser(driver);
        reports.flush();
    }
    @AfterMethod
    public void teardownScreenshot(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE) {
//            utils.captureScreenshot("failure.png");
            utils.addLog("FAIL", "Failure while exceution");
            extlogger.fail("TestcaseFailed", MediaEntityBuilder.createScreenCaptureFromPath(utils.captureScreenshot("Fail.png")).build());
        }
//        }else if(result.getStatus()==ITestResult.SUCCESS)
//        {
////        {     utils.addLog("PASS","Pass while exceution");
//              extlogger.pass("Testcase Passed", MediaEntityBuilder.createScreenCaptureFromPath(utils.captureScreenshot("Pass.png")).build());
//        }
//        else if(result.getStatus()==ITestResult.SKIP)
//        {
////        {     utils.addLog("SKIP","Skipped while exceution");
//            extlogger.skip("TestcaseFailed", MediaEntityBuilder.createScreenCaptureFromPath(utils.captureScreenshot("Skip.png")).build());
//        }
        reports.flush();

    }
}
