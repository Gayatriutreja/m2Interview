package utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class utils extends Base{
//    public static ExtentTest extlogger = reports.createTest("start");
    public static void addLog(String sStatus, String sMessage)
    {
    try{

            switch(sStatus)
            {
                case "INFO": {
                    extlogger.log(Status.INFO, sMessage);break;
                }
                case "PASS": {
                    extlogger.log(Status.PASS, sMessage);break;
                }
                case "FAIL": {
                    extlogger.log(Status.FAIL, sMessage);
                    break;
                }
                case "ERROR": {
                    extlogger.log(Status.ERROR, sMessage);break;
                }
                case "SKIP": {
                    extlogger.log(Status.SKIP, sMessage);break;
                }
                default :
                    extlogger.log(Status.INFO, "Enter correct Status for log method");
                    extlogger.info("Enter correct Status for log method");
            }
        } catch (Exception e) {
        System.out.println("Exception while adding log"+e);
        }
    }
    public static String captureScreenshot(String screenshotname) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String  screenshotPath=System.getProperty("user.dir")+"/Screenshots/" + BusinessReusable.currentdate()+ screenshotname;
        try {
            FileHandler.copy(src, new File(screenshotPath));
            utils.addLog("PASS", "Screenshot captured");
        } catch (Exception e) {
            utils.addLog("ERROR", "Unable to caption screenshot " + e);
        }
        return screenshotPath;
    }

}
