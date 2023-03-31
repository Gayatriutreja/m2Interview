package automationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Base;
import utility.BusinessReusable;
import utility.utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo extends BusinessReusable {
    By boardTab= By.xpath("//span[text()='Testing Board']");
    By pageheadervalidation= By.xpath("//h1[text()='Testing Board']");
    By addproject=By.xpath("(//h4[text()='Next month']//following::input[@placeholder='+ Add project'])[1]");
    String personname="//span[text()='name']/following::img[@class='person-bullet-image person-bullet-component']";
    By id=By.xpath("//input[@id='combobox-search']");
    String dropdownvalue="(//div[contains(@id,'combobox-item')])[1]";
    String status="//span[text()='name']/following::div[@data-walkthrough-id='status-cell']";
    By statusValue=By.xpath("//li//span[text()='Done']");
    String date="//span[text()='name']/following::div[@class='date-cell-component ']";
    String selectdate="//button[contains(@aria-label,'prevdate')]";
    By rows=By.xpath("//div[@class='pulse-component pulseComponent--dbO5k single-item-height grid-pulse with-menu can-edit']");
    public void addProject(String projName, String perName) {
        try{ //to validate the header of the page
            if (visibilityOfElmnt(pageheadervalidation, "pageHeader", 1000)) {
                projectDetails(projName,perName);
            }else {
                //if page header is not available then it will click on testing board to procced
                clickElement(boardTab, "Testing Board Tab");
                projectDetails(projName,perName);
            }}
        catch(Exception e)
        {
            utils.addLog("ERROR","Exception+"+e);
        }




    }

    /*************************************************************************************************************
     Method Name         :  projectDetails
     Parameters          :   projectName,personName
     Description By       :  function to add project details.
     ************************************************************************************************************
     */
    public void projectDetails(String projectName, String personName) throws AWTException {;

        String projectNameFinal="projectName_" + currentdate();
        Assert.assertTrue(clickElement(addproject, "addProject"));
        Assert.assertTrue(enterKeys(addproject,projectNameFinal , "ProjectName"));
        //to Navigate to next Tab and enter person Name
        tab();
        Assert.assertTrue(clickElement(By.xpath(personname.replace("name",projectNameFinal)), "Name"));
        Assert.assertTrue(enterKeys(id, personName, "Id"));
        Assert.assertTrue(clickElement(By.xpath(dropdownvalue.replace("value", personName)), "Drop down Value"));
        //to Navigate to next Tab and select status
        tab();
        Assert.assertTrue(clickElement(By.xpath(status.replace("name", projectNameFinal)), "Status"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(clickElement(statusValue, "Status Value"));
        //to Navigate to next Tab and select date
        tab();
        Assert.assertTrue(clickElement(By.xpath(date.replace("name", projectNameFinal)), "Date"));
        clickElement(By.xpath(selectdate.replace("prevdate",findPrevDay())),"Selected Date: "+findPrevDay() );        String date=findPrevDay();

    }
    public void tab() throws AWTException { Robot rob=new Robot();
        rob.keyPress(KeyEvent.VK_TAB);
        rob.keyRelease(KeyEvent.VK_TAB);
    }
}


