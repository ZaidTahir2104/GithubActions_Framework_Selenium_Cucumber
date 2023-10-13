package com.digital.util;

import com.aventstack.extentreports.Status;
import com.digital.ui.runner.ParallelExecutionRunnerTest;
import com.google.common.base.Function;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.jvnet.winp.WinProcess;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * AuthorName: Muhammad Zaid Tahir
 * AuthorEmail: mzaid@nisum.com
 * Summary : Common Utilities Page
 * CreatedDate : Oct-11-2023
 */

public class CommonUtilities {
    public static final int VERY_LONG_TIMEOUT = 60;
    public static final int LONG_TIMEOUT = 30;
    public static final int MEDIUM_TIMEOUT = 20;
    public static final int SHORT_TIMEOUT = 10;
    public static final int VERY_SHORT_TIMEOUT = 3;
    public static final String RWDDEVICE = "rwdDevice";
    public static final String OSCO_MONGODB_CONNECTION = "mongodb://mongodb-oscooscoqa:QXoFHXmGkFqek0AhpI0ZhA57xO4yh5IIPg9DImt4m6O3Bi8FUNvfIXhWdT233x6tEwfwcZrGrx4h7UMS662Mjw==@mongodb-oscooscoqa.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&maxIdleTimeMS=120000&appName=@mongodb-oscooscoqa@";
    //private static final Logger logger = Logger.getLogger(CommonUtilities.class);
    private static WebElement wait;

    public static boolean ClickByJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
            executor.executeScript("arguments[0].click();", element);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void clickElement(WebDriver driver, WebElement element) {
        assertNotNull("Element should not be null for click", element);
        waitForClickability(driver, element);
        element.click();
    }

    public static boolean isElementVisible(WebElement ele) {
        boolean elementDisplayed = false;
        try {
            ele.isDisplayed();
            elementDisplayed = true;
        } catch(Exception e) {
            elementDisplayed = false;
        }
        return elementDisplayed;
    }

    /**
     * This method is for validating the title of the page
     *
     * @param driver       - the WebDriver to actioned on
     * @param expectedText - expected value
     */
    public static void validateTitleOfThePage(WebDriver driver, String expectedText) {
        assertTrue("Title does not contain the expected string :"
                + expectedText, driver.getTitle().contains(expectedText));
    }

    public static String generateRandomEmail() {
        String Randomresult = RandomStringUtils.random(64, false, true);
        Randomresult =
                RandomStringUtils.random(3, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
        String mydate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
        String mailID = Randomresult + mydate + "@example.com";
        return mailID;
    }

    public static String generateRandomUsername() {
        String Randomresult = RandomStringUtils.random(64, false, true);
        Randomresult =
                RandomStringUtils.random(3, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
        String mydate = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
        String randomUsername = Randomresult + mydate;
        return randomUsername;
    }

    public static void assertFalseAndReport(
            boolean condition, String passMessage, String failMessage) {
        if (!condition) {
            report(Status.PASS, passMessage);
        } else {
            report(Status.FAIL, failMessage);
        }

        Assert.assertFalse(failMessage, condition);
    }

    public static void assertTrueAndReport(
            boolean condition, String passMessage, String failMessage) {
        if (condition) {
            report(Status.PASS, passMessage);
        } else {
            report(Status.FAIL, failMessage);
        }

        Assert.assertTrue(failMessage, condition);
    }

    public static void report(Status status, String message) {
        ExtentReportUtil.getExtentReportUtil().reportExecutionStatus(status, message);
    }

    public static String checkCSSbyJS(WebDriver driver, WebElement element, String attribute, String property) {
        String value =
                ((JavascriptExecutor) driver)
                        .executeScript(
                                "return window.getComputedStyle(arguments[0], '"
                                        + attribute
                                        + "').getPropertyValue('"
                                        + property
                                        + "');",
                                element)
                        .toString();
        return value;
    }

    public static void closeWindowandSwitchtoParent(WebDriver driver, String ParentWindow) {
        Set<String> handles = driver.getWindowHandles();

        if (handles.size() > 1) {

            driver.close();
            driver.switchTo().window(ParentWindow);
        }
    }

    public static void doubleClick(WebDriver driver, WebElement element) {
        Actions dragger = new Actions(driver);
        dragger.doubleClick().build().perform();
    }

    public static void enterText(WebDriver driver, WebElement element, String text) throws Exception {
        element.clear();
        ClickByJSS(driver, element);
        Thread.sleep(1000 / 2);
        element.sendKeys(text);
    }

    public static void enterText(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public static void clearText(WebElement element) {
        element.click();
        element.clear();
    }

    public static void scrollToElement(WebDriver driver,WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void getText(WebElement element, String expected) {
        assertNotNull("WebElement should not be null", element);
        assertNotNull("Expected string should not be null", expected);

        assertTrue(String.format("expected: %s, actual: %s ", expected, element.getText()), element.getText().contains(expected));
    }


    public static void waitTime(int waitTime) {
        TimeUnit.SECONDS.toMillis(waitTime);
    }

    public static boolean isElementPresent(WebElement element) {
        if (element != null) {
            return (isDisplayedViaCss(element) || isVisibleViaCss(element));
        }
        return false;
    }

    public static boolean isDisplayedViaCss(WebElement givenElement) {
        String[] displayValues = {"block", "flex", "inline-block"};
        try {
            if (givenElement != null) {
                System.out.println("Retrieved css display value is : " + givenElement.getCssValue("display"));
                if (givenElement.getCssValue("display").equals("none")) {
                    System.out.println("Display value is none");
                    return false;
                }

                for (String displayValue : displayValues) {
                    System.out.println("Retrieved css display value is : " + givenElement.getCssValue("display"));
                    if (givenElement.getCssValue("display").equals(displayValue)) {
                        System.out.println("Element display value : " + displayValue);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(" Exception occured in isDisplayedViaCss ");
            return false;
        }

        return false;
    }

    public static boolean isVisibleViaCss(WebElement givenElement) {
        try {
            if (givenElement != null) {
                System.out.println("Element visibility retrieved is : " + givenElement.getCssValue("visibility"));
                if (givenElement.getCssValue("visibility").equals("hidden")) {
                    System.out.println("Given element visibility is hidden");
                    return false;
                } else if (givenElement.getCssValue("visibility").equals("visible")) {
                    System.out.println("Given element visibility is visible");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(" Exception occured in isVisibleViaCss ");
            return false;
        }
        return false;
    }

    public static boolean isElementDisplayed(WebElement element) {
        if (element == null) return false;
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException | NullPointerException e) {
            return false;
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element, int... timeoutSeconds) {
        Integer timeout = timeoutSeconds.length > 0 ? timeoutSeconds[0] : SHORT_TIMEOUT;
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);;window.scrollBy(0, -120)", element);
        try {
            // Wait for element visibility
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until((WebDriver d) -> ExpectedConditions.visibilityOf(element).apply(d));
        } catch (StaleElementReferenceException ex) {
            // Handle StaleElementReferenceException by waiting again
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until((WebDriver d) -> ExpectedConditions.visibilityOf(element).apply(d));
        }
    }

    /***
     * This selects dropdown options based on the given index
     * @param element
     * @param index
     */
    public static void selectByIndexFromDropdown(WebElement element, int index) {
        Select dropdown = new Select(element);
        dropdown.selectByIndex(index);
    }

    public static String selectFirstSelectedOptionFromDropdown(WebElement element) {
        Select dropdown = new Select(element);
        return dropdown.getFirstSelectedOption().getText();
    }

    public static void switchWindow(WebDriver driver, String parentWindow) {
        Set<String> handles = driver.getWindowHandles();
     //   logger.info("Window handles : " + handles);
        if (handles.size() > 1) {
            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);
                }
            }
        }
    }

    public static WebElement waitForClickability(WebDriver driver, WebElement element) {
        waitUntilElementIsDisplayed(element);
        WebDriverWait wait = new WebDriverWait(driver,30);
        return wait.until((WebDriver d) -> {
            WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
            if (e != null && e.isDisplayed()) {
                return e;
            }
            return null;
        });
    }

    public static void ClickByJSS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        System.out.println("Element clicked");
    }

    public static WebElement waitForClickability(WebDriver driver, WebElement element, int timeoutSeconds) {
        System.out.println(String.format("waitForClickability timeoutSeconds: %s", timeoutSeconds));
        WebDriverWait wait = new WebDriverWait(driver,timeoutSeconds);
        return wait.until((WebDriver d) -> {
            WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
            if (e != null && e.isDisplayed()) {
                return e;
            }
            return null;
        });
    }

    public static boolean waitUntilElementIsDisplayed(WebElement element) {
        int counter = 0;
        boolean result = false;

        while (counter <= 2) {
            try {
                if (element.isDisplayed()) {
                    result = true;
                    break;
                }
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            counter++;
        }

        return result;
    }
    public static boolean waitForInVisibility(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until((WebDriver d) -> {
                WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
            return true;  // Element became invisible within the specified timeout
        });
        }catch (TimeoutException e) {
            return false;  // Element was still visible after the specified timeout
        }
        return false;
    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        return wait.until((WebDriver d) -> {
            WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
            if (e != null && e.isDisplayed()) {
                return e;
            }
            return null;
        });
    }


    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int... timeoutSecs) {
        Integer timeout = timeoutSecs.length > 0 ? timeoutSecs[30] : LONG_TIMEOUT;
        System.out.println("Mayank Timeout : "+timeout);
        try {
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            return wait.until((WebDriver d) -> {
                WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
                if (e != null && e.isDisplayed()) {
                    return e;
                }
                return null;
            });
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
          //  logger.info("Inside waitForVisibility catch");
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            return wait.until((WebDriver d) -> {
                WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
                if (e != null && e.isDisplayed()) {
                    return e;
                }
                return null;
            });
        }
    }

    public static void waitFluentForVisibility(WebDriver driver, WebElement element, int timeoutSecs) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSecs))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        try {
             wait.until((WebDriver d) -> {
                WebElement e = ExpectedConditions.elementToBeClickable(element).apply(d);
                if (e != null && e.isDisplayed()) {
                    return e;
                }
                return null;
            });
        } catch (Exception e) {
            Assert.fail("Page is not navigating to Appointment Confimation page");
        }
    }

    public static List<WebElement> waitForVisibility(WebDriver driver, List<WebElement> elements, int timeout) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class);

        return wait.until(d -> {
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return elements;
                }
            }
            return null;
        });
    }

    public static String xpathTextLower() {
        return "translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')";
    }



    public static boolean waitForElementVisibility(WebDriver driver, WebElement element, int timeout) {
        return getWebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element)) != null;
}

    public static WebDriverWait getWebDriverWait(WebDriver driver, int timeout) {
        Duration duration = Duration.ofSeconds(timeout);
        return new WebDriverWait(driver, duration.getSeconds());
    }

    public static boolean waitForElementTextValue(WebDriver driver, WebElement element, String value, int timeout) {
        WebDriverWait wait = getWebDriverWait(driver, timeout);

        ExpectedCondition<Boolean> elementTextContainsString = arg0 -> {
            try {
                return element.getText().equalsIgnoreCase(value);
            } catch (StaleElementReferenceException sere) {
                return false;
            }

        };
        wait.until(elementTextContainsString);

        System.out.println("===>>" + element.getText());

        boolean status = element.getText().equalsIgnoreCase(value);

        return status;

    }


    /**
     * Utility to get the WebElement to avoid the Stale element.
     * Get the element from either @WebDriver or WebElement as parent.
     * It tries for three time if it throws the stale element.
     *
     * @param parent Parent element either WebDriver or WebElement
     * @param by     By Selector
     * @return It will return WebElement if it is not stale otherwise null;
     */
    public static WebElement getElement(SearchContext parent, By by) {
        boolean staleElement = true;
        int tries = 3;
        WebElement element = null;
        while (staleElement && tries-- > 0) {
            try {
                element = parent.findElement(by);
                staleElement = false;

            } catch (StaleElementReferenceException e) {
                System.out.println(e.getMessage());
                staleElement = true;
            }
        }
        return element;
    }

    public static boolean isElementClickable(WebElement el, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.until((WebDriver d) -> {
                WebElement e = ExpectedConditions.elementToBeClickable(el).apply(d);
                return true;  // Element became invisible within the specified timeout
            });
        }catch (TimeoutException e) {
            return false;  // Element was still visible after the specified timeout
        }
        return false;
    }

    public static String getElementBackgroundColor(WebElement element) {
        String buttonColorHex = org.openqa.selenium.support.Color.fromString(element.getCssValue("background-color")).asHex();
        return buttonColorHex;
    }



    public static boolean isLoadComplete(WebDriver driver) {
        return ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("loaded")
                || ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete");
    }

    /**
     * Waits for the page to load. Polling each second for timeoutSecs.
     *
     * @param driver      - driver
     * @param timeoutSecs - timeout
     * @throws InterruptedException
     */
    public static void waitForThePageToLoad(WebDriver driver, int... timeoutSecs) throws InterruptedException {
        Integer timeout = timeoutSecs.length > 0 ? timeoutSecs[0] : MEDIUM_TIMEOUT;
        Calendar end = Calendar.getInstance();
        end.add(Calendar.SECOND, timeout);

        while (Calendar.getInstance().before(end)
                && !isLoadComplete(driver)) {
            Thread.sleep(1000);
        }

        Assert.assertTrue(String.format("isLoadComplete should be true. URL: %s. timeout: %s. ",
                        driver.getCurrentUrl(), timeout),
                isLoadComplete(driver));
    }

    public static void browseForWindowsPopUp(String strFilePath) throws Exception {
        Robot robot = new Robot();
        StringSelection stringSelection = new StringSelection(strFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        CommonUtilities.wait(CommonUtilities.VERY_SHORT_TIMEOUT);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        CommonUtilities.wait(CommonUtilities.VERY_SHORT_TIMEOUT);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        CommonUtilities.wait(CommonUtilities.VERY_SHORT_TIMEOUT);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        CommonUtilities.wait(CommonUtilities.VERY_SHORT_TIMEOUT);
    }

    public static int getRandomIntegerBetweenRange(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    public static void waitAndClickElement(WebDriver driver, WebElement element, int timeOutInSecs) {
        waitForClickability(driver, element, timeOutInSecs);
        try {
            ClickByJSS(driver, element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void captureScreenshotWithCurrentView(WebDriver driver, Scenario scenario) {
        try {
            final byte[] screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //scenario.embed(screenshot, "image/png");
        } catch (Exception e) {
        }
    }

    public static void captureScreenshot(WebDriver driver, Scenario scenario) {
        //scenario.embed(captureScreenshot(driver), "image/png");
    }

    public static byte[] captureScreenshot(WebDriver driver) {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("$('html').css('overflow-x', 'hidden');");
        jse.executeScript("$('.sticky-header-desktop.full-bleed-container').css('position', 'relative');");
        jse.executeScript("$('.menu-nav__wrapper').css('position', 'relative');");

        AShot shot = new AShot();
        String browser = System.getProperty("browser");
        switch (browser.toLowerCase()) {
            case "chrome":
                shot = shot.shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.5f), 100));
                break;
            case "chromeheadless":
                shot = shot.shootingStrategy(ShootingStrategies.viewportPasting(100));
                break;
            case "chromespoofer":
            case "chromespooferheadless":
                shot = shot.shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(3f), 100));
                break;
        }

        Screenshot screen = shot.takeScreenshot(driver);
        BufferedImage originalImage = screen.getImage();
        byte[] screenshot = new byte[0];

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            screenshot = baos.toByteArray();
        } catch (IOException noScreenshot) {
            System.out.println("Not able to take screenshot");
        }

        jse.executeScript("$('html').css('overflow-x', 'scroll');");

        return screenshot;
    }

    public static void NavigateTo(WebDriver driver, String url) throws InterruptedException {
        try {
            driver.navigate().to(url);
        } catch (TimeoutException ex) {
            driver.navigate().to(url);
        }
        CommonUtilities.isLoadComplete(driver);
        CommonUtilities.waitUtilPageLoad(driver);
    }

    public static void waitUntilElementIsEnabled(WebDriver driver, WebElement element,int timeout) {
        getWebDriverWait(driver,timeout).until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    if ((element.isDisplayed()) && (!element.getAttribute("innerHTML").contains("disabled"))) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            }
        });
    }
    public static void waitUntilElementIsDisabled(WebDriver driver, WebElement element, int timeout) {
        getWebDriverWait(driver,timeout).until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    if (!element.isEnabled()) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                return false;
            }
        });
    }

    /**
     * @param folderPath
     */
    public static void purgeFilesInAFolder(String folderPath) {
        File dir = new File(folderPath);
        try {
            for (File file : dir.listFiles()) {
                Assert.assertTrue(file.delete());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyFileInAFolder(String fileName, String folder) {
        File dir = new File(folder);
        File[] listOfFiles = dir.listFiles();
        boolean flag = false;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                if (listOfFiles[i].getName().equalsIgnoreCase(fileName)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * gets the element color
     **/
    public static String getElementColor(WebElement element) {
        return org.openqa.selenium.support.Color.fromString(element.getCssValue("color")).asHex();
    }

    public static void waitUtilPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete") && ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0")));
            }
        };
        try {
            Thread.sleep(1000);
            Duration  duration = Duration.ofSeconds(30);
            WebDriverWait waitForLoad = new WebDriverWait(driver, duration.getSeconds());
            waitForLoad.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    /***
     * This switches to a frame with frame id.
     */
    public static void switchframebyframeid(WebDriver driver, String Frameid) {
        driver.switchTo().frame(Frameid);
    }

    /***
     * This switches the control to default frame from your existing frame
     */

    public static void Switchframetodefault(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    /***
     * Executes the provided array in command
     * @param command - command to be executed
     * @return boolean value command is successfully executed or not
     */
    public static boolean executeCommand(String[] command, boolean isStoreDeploymentParallelTestRunning, boolean isParallelExecutionTestRunning) {
        Process p = null;
        boolean flag = true;
        try {
            p = Runtime.getRuntime().exec(command);

            if (isParallelExecutionTestRunning) {
                ParallelExecutionRunnerTest.processIds.add(getProcessId(p));
            }
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }

        while (line != null) {
            System.out.println(line);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
            }
        }

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String error;

        flag &= verifyCommandOutputMessages(stdInput);
        flag &= verifyCommandOutputMessages(stdError);

        return flag;
    }

    /***
     * Validates the command output messages for errors/exceptions
     * @param bufferedReader - Buffered reader object of the command
     * @return boolean value is command completed with errors/exceptions
     */
    public static boolean verifyCommandOutputMessages(BufferedReader bufferedReader) {
        String error;
        boolean flag = true;
        try {
            while ((error = bufferedReader.readLine()) != null) {
                if (error.toLowerCase().contains("exception") || error.toLowerCase().contains("error")) {
                    flag = false;
                }
                System.out.println(error);
            }
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }

        return flag;
    }

    /***
     * Deletes the specified directory
     * @param directory - directory as file object
     */
    public static void deleteDirectory(File directory) {

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                deleteDirectory(file);
            }

            deleteFile(directory);
        } else {
            deleteFile(directory);
        }
    }

    /***
     * Deletes the file
     * @param fileToBeDeleted - file object which needs to be deleted
     */
    public static void deleteFile(File fileToBeDeleted) {
        if (fileToBeDeleted.exists()) {
            fileToBeDeleted.delete();
        }
    }

    /***
     * copy the screenshots to the provided path
     * @param path - path where screenshots to be copied
     */
    public static void copyScreenshots(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files == null) {
            return;
        }

        Map<String, String> screenshotNamesMap = new LinkedHashMap<>();
        for (File sourceFile : files) {
            if (sourceFile.getName().startsWith("embedded") && sourceFile.getName().endsWith(".png")) {
                String targetScreenshotName = String.format("screenshot_%s.png", System.currentTimeMillis());
                screenshotNamesMap.put(sourceFile.getName(), targetScreenshotName);
                try {
                    FileUtils.copyFile(sourceFile, new File(path + File.separator + targetScreenshotName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                deleteFile(sourceFile);
            }
        }

        if (screenshotNamesMap.size() > 0) {
            String reportJsFile = path + File.separator + "report.js";
            String contents = null;
            try {
                contents = new String(Files.readAllBytes(Paths.get(reportJsFile)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int loopVar = 0; loopVar < screenshotNamesMap.size(); loopVar++) {
                contents = contents.replaceAll("embedded" + loopVar + ".png", screenshotNamesMap.get("embedded" + loopVar + ".png"));
            }

            PrintWriter prw = null;
            try {
                prw = new PrintWriter(reportJsFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            prw.println(contents);
            prw.close();
        }
    }

    /***
     * wait for specified seconds
     * @param secs - wait time in seconds
     */
    public static void wait(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Gets the process id of the process that is initiated for execution
     * @param p process object
     * @return running process id
     */
    private static Long getProcessId(Process p) {
        long pid = -1;
        try {
            //for windows
            if (p.getClass().getName().equals("java.lang.Win32Process") || p.getClass().getName().equals("java.lang.ProcessImpl")) {
                WinProcess wp = new WinProcess(p);
                pid = wp.getPid();
            }
            //for unix
            else if (p.getClass().getName().equals("java.lang.UNIXProcess")) {
                Field f = p.getClass().getDeclaredField("pid");
                f.setAccessible(true);
                pid = f.getLong(p);
                f.setAccessible(false);
            }
        } catch (Exception ex) {
            pid = -1;
        }

        return pid;
    }

    /***
     * wait until threads are started running
     * @return boolean value threads are started running or not
     */
    public static boolean waitUntilAllThreadsAreStarted(List<Long> processIds) {
        Instant start = Instant.now();
        Instant current = start;

        while (Duration.between(start, current).toMinutes() <= 5) {
            if (processIds.size() == 0) {
                wait(5);
            } else {
                break;
            }
            current = Instant.now();
        }

        return processIds.size() > 0;
    }

    /***
     * wait for the current threads to complete the execution
     * @return boolean value whether all threads are completed
     * @throws IOException
     */
    public static boolean waitUntilAllThreadsAreRunning(List<Long> processIds, int scriptsTimeoutInMins) throws IOException {

        boolean allThreadsCompleted = false;
        Instant start = Instant.now();
        Instant current = start;

        while (!allThreadsCompleted && Duration.between(start, current).toMinutes() <= scriptsTimeoutInMins) {
            for (int index = 0; index < processIds.size(); index++) {
                if (isProcessStillRunning(processIds.get(index))) {
                    wait(10);
                    //System.out.println("Threads are still running...");
                } else {
                    processIds.remove(index);
                }

                if (processIds.size() == 0) {
                    allThreadsCompleted = true;
                }
            }
            current = Instant.now();
        }

        return allThreadsCompleted;
    }

    /***
     * checks the process is still running
     * @param processId Process ID of the command
     * @return true if process is still running & false if process is completed
     * @throws IOException
     */
    public static boolean isProcessStillRunning(Long processId) throws IOException {

        try {
            String[] command = null;
            if (System.getProperty("os.name").contains("Windows")) {
                command = new String[]{"cmd", "/c", "tasklist /FI \"PID eq " + processId + "\""};
            } else {
                command = new String[]{"sh", "-c", "ps -p " + processId};
            }

            Process pr = Runtime.getRuntime().exec(command);

            InputStreamReader isReader = new InputStreamReader(pr.getInputStream());
            BufferedReader bReader = new BufferedReader(isReader);
            String strLine = null;
            while ((strLine = bReader.readLine()) != null) {
                if (strLine.contains(String.valueOf(processId))) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * This method is used for entering text in the textbox without clearing the text
     *
     * @param driver
     * @param element
     * @param text
     * @throws Exception
     */
    //This method is created to solve the issue in the update quantity, where clearing the webelement is creating issue
    public static void enterTextWithoutClearingAfterJSSClick(WebDriver driver, WebElement element, String text) throws Exception {
        ClickByJSS(driver, element);
        element.sendKeys(text);
        if (element.getText().length() != text.length()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].value=arguments[1];", element, text);
        }
    }

    /***
     * This function validates if any system properties exist in gradle command
     * whose value is different than the value passed in gradle.properties file
     * @param key - key passed in gradle command
     * @param defaultValue - value passed in gradle.property file
     * @return boolean
     */
    public static boolean isSystemPropertiesFound(String key, String defaultValue) {
        boolean fBool = System.getProperties().containsKey(key) && !System.getProperties().get(key).equals(defaultValue);
        return fBool;
    }

    /***
     * This method gets the system property value provided in run time
     * @param key - key passed in gradle command
     * @param defaultValue - value passed in gradle.property file
     * @return String
     */
    public static String getSystemPropertiesValue(String key, String defaultValue) {
        String systemPropertyValue = null;
        if (isSystemPropertiesFound(key, defaultValue)) {
            systemPropertyValue = System.getProperty(key);
        }
        return systemPropertyValue;
    }

    /***
     * This method gets the enviornment of the launched application during execution, i.e. stage / acceptance / production
     * @return String - Name of the enviornment
     */
    public static String getEnvironmentName() {
        String enviornment = System.getProperty("testdata").split("-")[1];
        return enviornment;
    }

    /***
     * This method waits till given Web Element to be displayed
     * @param element - WebElement
     * @throws Exception
     */
    public static void customWait(WebDriver driver, WebElement element) throws Exception {
        CommonUtilities.getWebDriverWait(driver, CommonUtilities.VERY_LONG_TIMEOUT * 2)
                .until(
                        new Function<WebDriver, Boolean>() {
                            public Boolean apply(WebDriver driver) {
                                try {
                                    return element.isDisplayed();
                                } catch (StaleElementReferenceException sere) {
                                    return false;
                                }
                            }
                        });
    }

    /***
     * This method formats the selected date slot into 00AM-00AM format
     * @param selectedDateSlot input value as 00 AM - 00 AM
     * @return String formatted in 00AM-00AM
     */
    public static String formattingTimeSlot(String selectedDateSlot) {
        String[] timeSlot = selectedDateSlot.split(" ");
        timeSlot[0] = StringUtils.leftPad(timeSlot[0], 2, "0");
        if (timeSlot.length > 2) {
            timeSlot[3] = StringUtils.leftPad(timeSlot[3], 2, "0");
        }
        String dateVal = "";
        for (int i = 0; i < timeSlot.length; i++) {
            dateVal = dateVal + timeSlot[i];
        }
        return dateVal;
    }

    /***
     * This method returns verifies the actual color with expected color for a list of WebElements
     * @param ELEMENT List of Webelements for which the color needs to be verified
     * @param expectedColor
     * @return true - if the comparison passes, false - if the comparison fails for any one of the webelement
     */
    public static boolean verifyElementColor(List<WebElement> ELEMENT, String expectedColor) {
        String actualColor;
        boolean fbool = false;
        for (WebElement ele : ELEMENT) {
            actualColor = CommonUtilities.getElementColor(ele);
            fbool = expectedColor.equalsIgnoreCase(actualColor);
        }
        return fbool;
    }

    /***
     * This method verifies the given cssProperty for a list of webelements with the given expected value
     * @param ELEMENT List of Webelements for which the cssProperty needs to be validated
     * @param cssProperty
     * @param expectedValue
     * @return if the comparison passes, false - if the comparison fails for any one of the webelement
     */
    public static boolean verifyElementCSSProperty(List<WebElement> ELEMENT, String cssProperty, String expectedValue) {
        boolean fbool = false;
        for (WebElement ele : ELEMENT) {
            fbool = ele.getCssValue(cssProperty).contains(expectedValue);
        }
        return fbool;
    }

    /***
     * This method verifies the text for a given list of web elements with the given expected value
     * @param ELEMENT List of Webelements for which the text needs to be validated
     * @param expectedValue
     * @return if the comparison passes, false - if the comparison fails for any one of the webelement
     */
    public static boolean verifyGetTextActualValueWithExpectedValue(List<WebElement> ELEMENT, String expectedValue) {
        boolean fbool = false;
        for (WebElement ele : ELEMENT) {
            System.out.println("ele.getText() " + ele.getText());
            if (ele.getText().toLowerCase().trim().contains(expectedValue)) {
                fbool = true;
                break;
            } else {
                fbool = false;
            }
        }
        return fbool;
    }

    /***
     * Tis method returns the current year
     * @return int : value - year
     */
    public static int getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        return year;
    }


    /***
     * This method checks the String value Null/Empty
     * * @param str
     * @return boolean
     */
    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /***
     * This method returns the current date
     * @return string date vaue
     */
    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /***
     * This method returns the date string with changed date format
     * @param dateString value is the input format that need to change
     * @return newDateString having new date format
     */
    public static String getNewDateFormat(String dateString) throws java.text.ParseException {
        final String OLD_FORMAT = "MM/dd/yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(dateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        return newDateString;
    }

    /***
     * This method will open new tab
     */
    public static void openNewTab(WebDriver driver) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");
    }

    public static void navigate(WebDriver driver, WebElement we) {
        String mainWindow = driver.getWindowHandle();
        CommonUtilities.scrollToElement(driver, we);
        CommonUtilities.clickElement(driver, we);
        CommonUtilities.switchWindow(driver, mainWindow);
    }

    public static String formatDate(String value) {
        try {
            String result, tempStr = value.substring(0, (value.indexOf(",") + 1));
            value = value.split(",")[1].trim();
            result = value.replaceFirst("^0+(?!$)", "");
            result = tempStr + " " + result;
            System.out.println("After removing leading zeros, date is=" + result);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void mouseHoverAndClick(WebDriver driver, WebElement element1, WebElement element2) {
        Actions hover = new Actions(driver);
        hover.moveToElement(element1).build().perform();
        hover.moveToElement(element2).build().perform();
        hover.click(element2).build().perform();
    }

    public static void cleanDriverLogs(WebDriver driver) {
        driver.manage().logs().get(LogType.PERFORMANCE).getAll();
    }

    /**
     * This method will wait for visibility of WebElement and then click the WebElement
     */

    public static void clickButton(WebDriver driver, WebElement element) {
        CommonUtilities.waitForVisibility(driver, element);
        CommonUtilities.scrollToElement(driver, element);
        CommonUtilities.clickElement(driver, element);
    }

    public static String getFilePath(Path strPath, String fileName) {
        Path filePath = null;
        if ((fileName.isEmpty())) {
            Assert.assertTrue("FileName can not be empty.", false);
        }
        filePath = Paths.get(strPath.toString(), fileName);
        return filePath.toString();
    }

    public static String getTodaysDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("d");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getNext30DaysDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 29);  // number of days to add

        SimpleDateFormat onlyDateDigitFormat = new SimpleDateFormat("d");
        return onlyDateDigitFormat.format(c.getTime());
    }

    public static boolean isClickable(WebDriver driver, WebElement element) {

//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//            wait.until(ExpectedConditions.elementToBeClickable(element));
//            System.out.println("Selected");
//            return true;
//        } catch (Exception e) {
//            System.out.println("Deselected");
//            return false;
//        }
        return false;
    }

    public static void isSelectedForCheckBox(WebDriver driver, WebElement element) {
        if (element.getAttribute("ng-reflect-checked").equals("true")) {
            System.out.println(element.getText());
        } else {
            System.out.println("vaccines are Not Selected");
        }
    }

    public static void getAttribute(WebElement element, String type) {
        if (element.getAttribute("ng-reflect-checked").equals("true")) {
            System.out.println(element.getText());
        } else {
            System.out.println("vaccines are Not Selected");
        }
    }

    public static void isUnselectedForCheckBox(WebDriver driver, WebElement element) {
        if (element.getAttribute("ng-reflect-disabled").equals("true")) {
            System.out.println("All 5 Vaccines are selected");
        } else {
            System.out.println("User can select more than 5 Vaccines");
        }
    }

    public static void isSelectedForDays(WebDriver driver, WebElement element) {
        if (element.getAttribute("class").equals("day-label active")) {
            System.out.println("Element is populated");
        } else {
            System.out.println("Element is not populated");
        }
    }

    public static void pressTab(WebDriver driver) {
        Actions a = new Actions(driver);
        a.sendKeys(Keys.TAB).build().perform();
    }

    public static void pressEnter(WebDriver driver) {
        Actions a = new Actions(driver);
        a.sendKeys(Keys.ENTER).build().perform();
    }

    public static String sessionStorage(WebDriver driver, String key) {
        return webStorage(driver).getSessionStorage().getItem(key);
    }

    private static WebStorage webStorage(WebDriver driver) {
        return (WebStorage) new Augmenter().augment(driver);

    }

    public static void clickWebElement(WebDriver driver, WebElement element) {
        assertNotNull("WebElement should not be null for click", element);
        waitTime(1000);
        element.click();
    }

    public static void scrollToPageTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }
}