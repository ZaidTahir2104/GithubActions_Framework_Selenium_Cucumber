package com.digital.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.digital.ui.steps.Hook;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

/**
 *AuthorName: Muhammad Zaid Tahir
 *AuthorEmail: mzaid@nisum.com
 *Summary : Screenshot utils Page
 *CreatedDate : Oct-11-2023
 */

public class ExtentReportUtil {

  private static ExtentReports extent;
  private static final String reportFileName =
      System.getProperty("user.dir")
          + File.separator
          + "ExtentReports"
          + File.separator
          + "ExecutionReport" + System.getProperty("threadName") + ".html";
  private static ExtentReportUtil reportUtil;
  private static String scenarioName = "";
  private static ExtentTest test;

  ExtentReportUtil() {

    ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFileName);

    htmlReporter.config().setDocumentTitle("My Teams Automation HTML Report");
    htmlReporter.config().setReportName("My Teams Automation Execution Report");
    htmlReporter.config().setTheme(Theme.STANDARD);

    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
    extent.setSystemInfo("OS", System.getProperty("os.name"));

    try {
      extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    extent.setSystemInfo("User Name", System.getProperty("user.name"));
    extent.setSystemInfo("Browser", System.getProperty("browser"));
    extent.setSystemInfo("Banner", System.getProperty("testdata").split("-")[0]);
    extent.setSystemInfo("Environment", System.getProperty("testdata").split("-")[1]);
  }

  public static ExtentReportUtil getExtentReportUtil() {
    if (reportUtil == null) {
      deleteHtmlReport();
      reportUtil = new ExtentReportUtil();
    }

    if (!scenarioName.equalsIgnoreCase(Hook.testName)) {
      test = extent.createTest(Hook.testName);
      test.getModel().setStartTime(Calendar.getInstance().getTime());
      scenarioName = Hook.testName;
    }

    return reportUtil;
  }

  private static void deleteHtmlReport() {
    File reportFile = new File(reportFileName);
    if(reportFile.exists()) {
        reportFile.delete();
    }
  }

  public void generateReport() {
    test.getModel().setEndTime(Calendar.getInstance().getTime());
    extent.flush();
  }

  public void reportExecutionStatus(Status status, String message){
    test.log(status, message);
  }

  @Deprecated // Used the other overloaded method.
  public void reportExecutionStatus(String statusStr, String message) {
    Status status = Status.valueOf(statusStr.trim().toUpperCase());
    reportExecutionStatus(status, message);
  }
}
