package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BusinessReusable extends Base {
    public Boolean enterKeys(By fieldxpath, String enteredvalue, String fieldName) {
        boolean bFieldIsEditable = false;

        try {
            WebElement element = driver.findElement(fieldxpath);
            bFieldIsEditable = visibilityOfElmnt(fieldxpath, fieldName, 60);
            if (bFieldIsEditable) {
                element.clear();
                element.sendKeys(enteredvalue);
                utils.addLog("PASS",fieldName+" enter value: "+enteredvalue);
            }
        } catch (Exception ex) {
            System.out.println("Exception while validating the element " + ex);
        }
        return bFieldIsEditable;
    }

    public Boolean clickElement(By fieldxpath, String elementName)
    {
        boolean fieldisClickable = false;

        try {
            WebElement element = driver.findElement(fieldxpath);
            fieldisClickable = visibilityOfElmnt(fieldxpath, elementName, 60);
            if(fieldisClickable)
            {
                element.click();
                utils.addLog("PASS",elementName+" is clicked");
            }
        }catch(Exception e)
        {
            utils.addLog("FAIL","Unable to click on "+elementName);
        }
        return fieldisClickable;
    }


    public static boolean switchBackFromFrame() {
        boolean bframeStatus = false; // Declared and initialized to false to assign return value
        try {
            driver.switchTo().defaultContent();

            bframeStatus = true;
        } catch (Exception e) {
            utils.addLog("Error","Exception: "+e); // function to handle the exceptions
        }
        return bframeStatus;
    }


    public static boolean webElementDropDown(WebElement eleDropDown, String sValueInTxtField, String sElementName) {
        boolean bIsDropDownExist = false; // Declared and initialized to false to assign return value
        try {
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            if (eleDropDown.isDisplayed()) {
                eleDropDown.sendKeys(sValueInTxtField);
                utils.addLog("PASS", "Choosen " + sValueInTxtField + " from dropdown list box " + sElementName);
                bIsDropDownExist = true;
            } else {
                utils.addLog("FAIL", sElementName + " Element Doesn't exist");
            }
        } catch (Exception e) {
            utils.addLog("ERROR","Exception:" +e); // function to handle the exceptions
        }
        return bIsDropDownExist; // returning boolean value based on action performed on dropdown
    }

    /*************************************************************************************************************
     Method Name         :  performSendKey
     Parameters          :   eleObj,sKeyType
     Description By       :  function to perform keyboard actions like SHIFT, ENTER etc.
     ************************************************************************************************************
     * @return*/
    public static boolean performSendKey(WebElement eleObj, String sKeyType) {
        boolean bReturnSendKey = false;
        try {
            switch (sKeyType) {
                case "ENTER":
                    eleObj.sendKeys(Keys.ENTER);
                    break;
                case "SHIFT":
                    eleObj.sendKeys(Keys.SHIFT);
                    break;
                case "CLEAR":
                    eleObj.sendKeys(Keys.CLEAR);
                    break;
                default:
                    utils.addLog("INFO", "kindly pass the valid KeyType.");
            }

            bReturnSendKey = true;
            utils.addLog("INFO", sKeyType + " Sendkey Performed");
        } catch (Exception e) {
            utils.addLog("ERROR","Exception: "+e);// function to handle the exceptions
        }
        return bReturnSendKey;
    }

    public static boolean visibilityOfElmnt(By by, String sElementName, int timeout) {
        boolean bElmntVisible = false;
        WebElement weObj = null;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
//        FluentWait<WebDriver> fluentWait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        try {

            weObj = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            // if weObj size is not equals to zero, then element is available hence assigning the value true
            if (!weObj.getSize().equals(0)) {
                bElmntVisible = true;
//                utils.addLog("PASS",sElementName +" is visible");


            }

        } catch (StaleElementReferenceException e) {

            utils.addLog("ERROR", "Stale Element Exception "+e);
        } catch (Exception e) {

            utils.addLog("ERROR", "Element not present "+e);
        }
        return bElmntVisible;// return value
    }
    public static boolean scrollIntoViewElement(WebElement eleListAcessType, String sElementName) throws
            IOException {
        boolean bReturnValue = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", eleListAcessType);
            utils.addLog("PASS", "scrollIntoView done for : " + sElementName);
            bReturnValue = true;
        } catch (Exception e) {
            utils.addLog("ERROR","Exception"+e); // function to handle the exceptions
        }
        return bReturnValue;
    }

    public HashSet<String> getuniqueListOfDropDownValue(WebElement fieldxpath)
    {
        Select dropdownxpath=new Select(fieldxpath);
        List<WebElement> dropdownValues=dropdownxpath.getOptions();
        HashSet<String> values=new HashSet<String>();
        for(int i=0;i<dropdownValues.size();i++)
        {
            values.add(dropdownValues.get(i).getText());
        }
        return values;
    }

    public void openNewTab()
    {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");

    }

    public void openNewWindow()
    {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.google.com");
    }
    public void openLinkNewTab(By lxpath)
    {
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        String n = Keys.chord(Keys.CONTROL, Keys.ENTER);
        driver.findElement(lxpath).sendKeys(n);
        System.out.println("link");
    }

    public static String currentdate() {
        DateFormat datefrmt = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentdate = new Date();
        return datefrmt.format(currentdate);

    }

    public static boolean switchToFrame(WebElement ele) {
        boolean bframeStatus = false; // Declared and initialized to false to assign return value
        try {
            driver.switchTo().frame(ele);

            bframeStatus = true;
        } catch (Exception e) {
            utils.addLog("Error","Exception: "+e); // function to handle the exceptions
        }
        return bframeStatus;
    }

    public void popupAlerts(String alertType,String value)
    {
        switch (alertType)
        {
            case "Dismiss":
                driver.switchTo().alert().dismiss();;
                break;
            case "accept":
                driver.switchTo().alert().accept();
                break;
            case "popupText":
                driver.switchTo().alert().getText();
                break;
            case "entervalues":
                driver.switchTo().alert().sendKeys(value);
                break;
        }

    }

    public void fileUploadusingRobotClass() throws AWTException {
    String FilePath=System.getProperty("user.dir")+"/FileUpload/Doc.pdf";
        StringSelection ss=new StringSelection(FilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
        Robot rob=new Robot();
        rob.keyPress(KeyEvent.VK_CONTROL);
        rob.keyPress(KeyEvent.VK_V);
        rob.keyRelease(KeyEvent.VK_V);
        rob.keyRelease(KeyEvent.VK_CONTROL);
        rob.keyPress(KeyEvent.VK_ENTER);
        rob.keyRelease(KeyEvent.VK_ENTER);
       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);


    }

    public void mousepresswithRobotClass(String EventName) throws AWTException {
         Robot rob=new Robot();
        switch(EventName)
        {
            case "rightclick":
                rob.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                rob.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                break;
            case "leftclick":
                rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                break;

            case "middleclick":
                rob.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                rob.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                break;

        }
    }

    public static String findPrevDay(){
     final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        Date date=new Date();
        SimpleDateFormat dateFrmt=new SimpleDateFormat("MMMM dd");
        Date previousdate=new Date(date.getTime() - MILLIS_IN_A_DAY);
        return dateFrmt.format(previousdate);
    }
}
