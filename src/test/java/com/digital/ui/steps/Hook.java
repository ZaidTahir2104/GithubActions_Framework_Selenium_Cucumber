package com.digital.ui.steps;

import com.digital.ui.factory.BrowserFactory;
import com.digital.ui.pages.BasePageObject;
import com.digital.util.CommonUtilities;
import com.digital.util.ExtentReportUtil;
import com.digital.util.ScreenShotCapture;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *AuthorName: Murali Krishnan Nagaraj
 *AuthorEmail: muralikrishnan.nagaraj@albertsons.com
 *Summary : Hooks Page - Before & After
 *CreatedDate : Nov-04-2018
 */


public class Hook extends BaseBrowser {

  public static String testName;
  ExtentReportUtil reportUtil;
  private final BaseBrowser baseBrowser;

  public Hook(BaseBrowser baseBrowser) {

    this.baseBrowser = baseBrowser;
  }

  @Before
  public void startUp(Scenario scenario) throws Exception {
    testName = scenario.getName();
    System.out.println("Starting scenario execution: " + testName);
    clearCollectionObjs();
    reportUtil = ExtentReportUtil.getExtentReportUtil();
    baseBrowser.webDriver = BrowserFactory.getBrowser(System.getProperty("browser"));
    String executionEnvironment =
        "<b>Execution Details:</b><br><br>Browser: "
            + System.getProperty("browser")
            + "<br>Banner: "
            + System.getProperty("testdata").split("-")[0]
            + "<br>Environment: "
            + System.getProperty("testdata").split("-")[1];
    reportUtil.reportExecutionStatus("INFO", executionEnvironment);

    BasePageObject.dataholderMap.put("scenarioObject", scenario);
    BasePageObject.dataholderMap.put("webdriver", baseBrowser.webDriver);
    baseBrowser.webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  @After
  public void tearDown(Scenario scenario) throws IOException {
    if (scenario.isFailed()) {
      //CommonUtilities.captureScreenshot(baseBrowser.webDriver, scenario);
      ScreenShotCapture screenShotCapture=new ScreenShotCapture();
      screenShotCapture.screenCapture(baseBrowser.webDriver,"scenario");
     // scenario.embed(
       //   ("Browser URL: " + baseBrowser.webDriver.getCurrentUrl()).getBytes(), "text/plain");

      reportUtil.reportExecutionStatus(
          "FAIL",
          "Scenario '"
              + scenario.getName()
              + "' is failed. Please check Jenkins console output for more details.");
    } else {
      reportUtil.reportExecutionStatus("PASS", "Scenario '" + scenario.getName() + "' is passed.");
    }

    System.out.println("Ending scenario execution: '" + scenario.getName() + "'. Execution Status: " + scenario.getStatus());

    clearCollectionObjs();

  //  baseBrowser.webDriver.close();
   baseBrowser.webDriver.quit();

     reportUtil.reportExecutionStatus("INFO", "***********************END OF TEST************************");

    reportUtil.generateReport();
  }


  public void clearCollectionObjs(){
      BasePageObject.dataholderMap.clear();
  }
}
