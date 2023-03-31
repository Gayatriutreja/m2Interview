package automationPages;

import org.openqa.selenium.By;

import org.testng.Assert;
import utility.BusinessReusable;
import utility.utils;

public class DemoLogin extends BusinessReusable {
    By username= By.xpath("//input[@id='user_email']");
    By password=By.xpath("//input[@id='user_password']");
    By login=By.xpath("//div[text()='Log in']");
    By pageVerification=By.xpath("//h1[@class='login-header']");


    public void pagelogin(String userNameValue, String passWordValue)
    {
        if(driver.findElement(pageVerification).isDisplayed()) {
            Assert.assertTrue(enterKeys(username, userNameValue, "Email"));
            Assert.assertTrue(enterKeys(password, passWordValue, "Password"));
            Assert.assertTrue(clickElement(login, "Login Button"));
        }
        else {
            utils.addLog("FAIL","Login Page is not displayed");
        }
    }
}
