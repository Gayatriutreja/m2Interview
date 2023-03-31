package testclasses;

import automationPages.Demo;
import automationPages.DemoLogin;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utility.BusinessReusable;
import utility.utils;

public class DemoProject extends BusinessReusable {
    @Test
    public void addprojectValues() {
        try {
            Demo project = PageFactory.initElements(driver, Demo.class);
            DemoLogin login = PageFactory.initElements(driver, DemoLogin.class);
            login.pagelogin(excelRead.getStringData("Login", 0, 0), excelRead.getStringData("Login", 0, 1));
            project.addProject(excelRead.getStringData("AddProject", 0, 0), excelRead.getStringData("AddProject", 0, 1));
        }catch (Exception e)
        {
            utils.addLog("FAIL","Error occured while executing"+ e);
        }
    }
}
