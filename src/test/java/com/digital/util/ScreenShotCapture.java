package com.digital.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 *AuthorName: Muhammad Zaid Tahir
 *AuthorEmail: mzaid@nisum.com
 *Summary : Screenshot utils Page
 *CreatedDate : Oct-11-2023
 */

public class ScreenShotCapture {
  public void screenCapture(WebDriver driver, String name) throws IOException {
    File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String path = "./target/screenshots/" + name + "_" + source.getName();
    FileUtils.copyFile(source, new File(path));
  }
}
