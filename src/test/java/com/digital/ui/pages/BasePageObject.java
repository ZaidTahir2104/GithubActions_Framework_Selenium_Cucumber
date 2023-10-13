package com.digital.ui.pages;

import com.aventstack.extentreports.Status;
import com.digital.util.CommonUtilities;
import com.digital.util.ExtentReportUtil;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.transform.CategoryASTTransformation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AuthorName: Murali Krishnan Nagaraj
 * AuthorEmail: muralikrishnan.nagaraj@albertsons.com
 * Summary : Base Page Objects Setup
 * CreatedDate : Nov-04-2018
 */


public class BasePageObject {
  //  private static final Logger logger = Logger.getLogger(BasePageObject.class);

    private final WebDriver driver;
    public static Map<String, Object> dataholderMap = new HashMap<>();

    public static List<String> a11yPageTracker = new ArrayList<>();

    public static final String USER_DIR = "user.dir";
    public static final String DOWNLOADED_FILES_FOLDER = "downloadFiles";

    public BasePageObject(WebDriver driver){
        //PropertyConfigurator.configure("log4j.properties");
        this.driver = driver;
    }


    public void logReport(Status status, String message){
        ExtentReportUtil.getExtentReportUtil().reportExecutionStatus(status, message);
    }

    /**
     * Gets the cookie object for usage.
     * @return jsonCookie
     */
    public static String getJsonCookie() {
        return jsonCookie;
    }

    /**
     * Sets the cookie object for further reference.
     * @param jsonCookie
     */
    public static void setJsonCookie(String jsonCookie) {
        BasePageObject.jsonCookie = jsonCookie;
        System.out.println(jsonCookie); //REMOVE BEFORE FLIGHT
    }

    private static String jsonCookie; //Used to play around with cookies.

    /**
     * Gets the cookie object for usage.
     * @return referenceJsonCookie
     */
    public static String getReferenceJsonCookie() {
        return referenceJsonCookie;
    }

    /**
     * Sets the cookie object for further reference.
     * @param referenceJsonCookie
     */
    public static void setReferenceJsonCookie(String referenceJsonCookie) {
        BasePageObject.referenceJsonCookie = referenceJsonCookie;
        System.out.println(jsonCookie); //REMOVE BEFORE FLIGHT
    }

    private static String referenceJsonCookie; //Used to play around with cookies.

    /**
     * Convenience method to compare two non empty elements of dataholderMap
     * @param leftKey  - one key to consider
     * @param rightKey - the other key to consider
     */
    public void isEqualDataHolderMap(String leftKey, String rightKey) {
        Assert.assertFalse(String.format("%s should not be empty", leftKey),
                StringUtils.isEmpty((String) dataholderMap.get(leftKey)));
        Assert.assertFalse(String.format("%s should not be empty", rightKey),
                StringUtils.isEmpty((String) dataholderMap.get(rightKey)));
    //    logger.debug(String.format("dataholderMap.get(%s): %s", leftKey, dataholderMap.get(leftKey)));
      //  logger.debug(String.format("dataholderMap.get(%s): %s", rightKey, dataholderMap.get(rightKey)));

        Assert.assertTrue(String.format("%s eq %s",leftKey, rightKey),
                ((String) dataholderMap.get(leftKey)).equalsIgnoreCase((String) dataholderMap.get(rightKey)));
    }

    /**
     * Convenience method to compare two non empty elements of dataholderMap
     * @param leftKey  - one key to consider
     * @param rightKey - the other key to consider
     */
    public void isDiffDataHolderMap(String leftKey, String rightKey) {
        Assert.assertFalse(String.format("%s should not be empty", leftKey),StringUtils.isEmpty((String) dataholderMap.get(leftKey)));
        Assert.assertFalse(String.format("%s should not be empty", rightKey), StringUtils.isEmpty((String) dataholderMap.get(rightKey)));
      //  logger.debug(String.format("dataholderMap.get(%s): %s", leftKey, dataholderMap.get(leftKey)));
        //logger.debug(String.format("dataholderMap.get(%s): %s", rightKey, dataholderMap.get(rightKey)));

        Assert.assertFalse(String.format("%s eq %s",leftKey, rightKey),((String) dataholderMap.get(leftKey)).equalsIgnoreCase((String) dataholderMap.get(rightKey)));
    }

    /**
     * Convenience method to save the value of an attribute of a webElement to the dataholderMap object
     * @param webElement     - the webElement
     * @param attributeName  - the attribute with the value to extract
     * @param key            - the dataholdetMap key to store the attribute value
     */
    public void saveToDataHolderMap(WebElement webElement, String attributeName, String key) {
        CommonUtilities.waitForVisibility(driver, webElement);
        dataholderMap.put(key, webElement.getAttribute(attributeName));
     //   logger.info(dataholderMap.toString());
    }

    public void saveTextToDataHolderMap(String value, String key) {
        String eleValue = value;
        String eleKey = key;
        dataholderMap.put(eleKey,eleValue);

        //dataholderMap.put(key, value);
        //logger.info(dataholderMap.toString());
    }

    public void clearInput(WebElement we){
        CommonUtilities.clearText(we);
    }

    //no git
    public void waitForElementToBecomeClickable(WebElement webElement) {
        CommonUtilities.waitForClickability(driver,webElement);
    }

    public void waitForElementToBecomeInvisible(WebElement webElement) {
        try {
            if (webElement.isDisplayed())
                CommonUtilities.waitForInVisibility(driver,webElement,60);
        }
        catch(Exception e){
            System.out.println("Element is not visible");
        }
    }

    public void waitForElementToBecomeVisible(WebElement webElement) {
        CommonUtilities.waitForVisibility(driver,webElement,60);
    }

    public void assertElementBackgroundColor(WebElement webElement, String expectedColor) {
        String actualColor = CommonUtilities.getElementBackgroundColor(webElement);
        Assert.assertEquals(expectedColor,actualColor);
    }

    public void assertElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse(String.format("%s should not be displayed", webElement),CommonUtilities.isElementDisplayed(webElement));
    }

    public void clickElement(WebElement we) throws Exception {
    //    logger.debug("Click: " + we);
        CommonUtilities.scrollToElement(driver, we);
        CommonUtilities.waitForClickability(driver, we);
        CommonUtilities.ClickByJSS(driver, we);
    }
}
