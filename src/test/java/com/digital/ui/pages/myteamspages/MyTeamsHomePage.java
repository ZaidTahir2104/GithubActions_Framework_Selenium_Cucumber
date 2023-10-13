package com.digital.ui.pages.myteamspages;

import com.digital.ui.pages.BasePageObject;
import com.digital.util.CommonUtilities;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyTeamsHomePage extends BasePageObject {

    @FindBys(@FindBy(css = "img[alt='Google']"))
    private static WebElement imageLogo;

    @FindBys(@FindBy(xpath = "//div[@class='banner-with-text-lg']"))
    private static WebElement vaccinationHeaderWithImage;

    @FindBys(@FindBy(xpath = "//h1[text()=\"First, we'll need some information\"]"))
    private static WebElement vaccinationSecondaryHeader;

    @FindBys(@FindBy(xpath = "//p[text()=\"This will help us to make sure that we're showing the appropriate vaccines for the person receiving the vaccination.\"]"))
    private static WebElement SecondaryHeaderWithFollowingText;

    @FindBys(@FindBy(xpath = "//button[@aria-label='close notification']"))
    private static WebElement hideNotification;

    @FindBys(@FindBy(id = "APjFqb"))
    private static WebElement searchField;

    @FindBys(@FindBy(id = "zipCode"))
    private static WebElement zipcode;

    @FindBys(@FindBy(id = "b2c-continue-btn"))
    private static WebElement continueButton;

    @FindBys(@FindBy(xpath = "//*[text()='Accept All']"))
    private static WebElement cookies;

    @FindBys(@FindBy(xpath = "//div[text()='Please enter a valid date of birth.']"))
    private static WebElement errorMgsForBlankDOB;

    @FindBys(@FindBy(xpath = "//button[@disabled='true']"))
    private static WebElement verifyContinueButtonDisabled;

    @FindBys(@FindBy(xpath = "//div[text()='Please enter your zip code.']"))
    private static WebElement errorMgsForBlankZipCode;

    @FindBys(@FindBy(xpath = "//div[text()='Please enter valid 5-digit Zip Code value.']"))
    private static WebElement errorMgsForInvalidZipCode;

    @FindBys(@FindBy(xpath = "//div[text()='Please enter a valid date of birth.']"))
    private static WebElement errorMgsForBefore1900YearDOB;

    @FindBys(@FindBy(xpath = "//div[text()='Please enter a valid date of birth.']"))
    private static WebElement errorMgsForFutureYearBlankDOB;

    @FindBys(@FindBy(xpath = "//div[text()='Please enter valid DOB. Vaccine recipient must be at least 2 years old.']"))
    private static WebElement errorMgsForAtLeast2Years;




    private final WebDriver driver;

    public MyTeamsHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void searchText(String text){
        CommonUtilities.scrollToElement(driver, searchField);
        CommonUtilities.enterText(searchField, text);
    }

    public void fillZipcode(String zipValue){
        CommonUtilities.enterText(zipcode, zipValue);
    }

    public void errorMgsForInvalidDOB(String DOB_error){
        waitForElementToBecomeVisible(errorMgsForBlankDOB);
        String actError = errorMgsForBlankDOB.getText();
        if (!actError.equalsIgnoreCase(DOB_error))
            Assert.fail("Should not allow blank input into DOB field");
    }

    public void verifyContinueButtonDisabled(){
        boolean continueButtonIsEnable = verifyContinueButtonDisabled.isEnabled();
        if (continueButtonIsEnable)
            Assert.fail("Continue Button should not be enable");
    }

    public void errorMsgBlankZipCode(String zipcode_error){
        waitForElementToBecomeVisible(errorMgsForBlankZipCode);
        String actError = errorMgsForBlankZipCode.getText();
        if (!actError.equalsIgnoreCase(zipcode_error))
            Assert.fail("Should not allow bank input into Zipcode field");
    }

    public void errorMgsForInvalidZipCode(String zipcode_error){
        waitForElementToBecomeVisible(errorMgsForInvalidZipCode);
        String actError = errorMgsForInvalidZipCode.getText();
        if (!actError.equalsIgnoreCase(zipcode_error))
            Assert.fail("Should not allow invalid input into Zipcode field");
    }

    public void errorMgsForBefore1900YearDOB(String DOBError){
        waitForElementToBecomeVisible(errorMgsForBefore1900YearDOB);
        String actError = errorMgsForBefore1900YearDOB.getText();
        if (!actError.equals(DOBError))
            Assert.fail("Should not allow before 1900 years of dates input into DOB field");
    }

    public void errorMgsForAtLeast2Year(String DOBError){
        waitForElementToBecomeVisible(errorMgsForAtLeast2Years);
        String actError = errorMgsForAtLeast2Years.getText();
        if (!actError.equals(DOBError))
            Assert.fail("Should not allow before 1900 years of dates input into DOB field");
    }

    public void errorMgsForFutureYearBlankDOB(String DOBError){
        waitForElementToBecomeVisible(errorMgsForFutureYearBlankDOB);
        String actError = errorMgsForFutureYearBlankDOB.getText();
        if (!actError.equals(DOBError))
            Assert.fail("Should not allow future dates input into DOB field");
    }

    public void b2cFillZipcode(String zipCode){
        CommonUtilities.enterText(zipcode, zipCode);
    }

    public void clickContinue() throws Exception{
        CommonUtilities.scrollToElement(driver, continueButton, 2);
        CommonUtilities.clickElement(driver, continueButton);
        Thread.sleep(1000);
        CommonUtilities.waitForThePageToLoad(driver, 5);
    }

    public String DOB(int year) {
         DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
         String[] s = dateFormat.format(new Date()).split("/");
        return s[0] + "/" + s[1] + "/" + (Integer.parseInt(s[2]) - year);
    }

    public void pressTab() {
        Actions a = new Actions(driver);
        a.sendKeys(Keys.TAB).build().perform();
    }

    public void user_closes_cookie_icon() {
        try {
            if (cookies.isDisplayed())
                CommonUtilities.clickElement(driver, cookies);
        } catch (Exception e) {
            System.out.println("Cookies Icon is not visible");
        }
    }

    public void googleImageLogo() {
        CommonUtilities.scrollToPageTop(driver);
        CommonUtilities.isElementDisplayed(imageLogo);

    }

    public void vaccinationHeaderWithImageatHomePage() {
        CommonUtilities.isElementDisplayed(vaccinationHeaderWithImage);
    }

    public void vaccinationSecondaryHeader() {
        CommonUtilities.isElementDisplayed(vaccinationSecondaryHeader);
    }

    public void secondaryHeaderWithFollowingText() {
        CommonUtilities.isElementDisplayed(SecondaryHeaderWithFollowingText);
    }
}
