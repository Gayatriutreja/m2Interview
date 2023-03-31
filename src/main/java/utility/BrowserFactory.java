package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory{
    public static WebDriver startApplication(WebDriver driver,String browsername, String url)
    {
        if(browsername.equals("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
            driver=new ChromeDriver();
            utils.addLog("PASS", "Browser opened successfully");
        }
        else if(browsername.equals("Firefox"))
        {

        }
        else if (browsername.equals("IE"))
        {

        }
        else {
            System.out.println("We do not suport the mentioned driver");

        }
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        return driver;
    }
public static void quitBrowser(WebDriver driver)
{
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    driver.quit();
    utils.addLog("PASS", "Closing the Browser");
}



}
