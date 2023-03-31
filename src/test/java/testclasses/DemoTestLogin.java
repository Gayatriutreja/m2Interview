package testclasses;

import automationPages.DemoLogin;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utility.BusinessReusable;

public class DemoTestLogin extends BusinessReusable {
    @Test
    public void loginfunction() {
        DemoLogin login = PageFactory.initElements(driver, DemoLogin.class);
        login.pagelogin(excelRead.getStringData("Login", 0, 0), excelRead.getStringData("Login", 0, 1));


    }
}