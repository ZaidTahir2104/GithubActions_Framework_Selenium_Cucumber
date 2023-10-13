package com.digital.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

  String filename;
  InputStream inputStream = null;
  String path = "ui/config/";
  Properties property = new Properties();

  public Properties getProperty(String fileName) {
    try {
      inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
      property.load(inputStream);
    } catch (IOException e) {
      System.err.println("Inside: propertyLoader: FileName: " + fileName + " " + e.getMessage());
      e.printStackTrace();
    }
    return property;
  }

  public Properties getProperty() {
    try {
      String testdata = System.getProperty("testdata");
      filename = path + testdata.split("-")[0] + "/" + testdata.split("-")[1] + ".properties";
      inputStream = getClass().getClassLoader().getResourceAsStream(filename);
      property.load(inputStream);
    } catch (Exception e) {
      System.err.println("Inside: propertyLoader: FileName: " + filename + " " + e.getMessage());
      e.printStackTrace();
    }
    return property;
  }
}
