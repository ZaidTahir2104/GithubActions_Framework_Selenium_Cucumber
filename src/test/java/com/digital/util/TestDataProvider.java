package com.digital.util;

import java.util.Properties;
import java.util.logging.Logger;


/**
 *AuthorName: Muhammad Zaid Tahir
 *AuthorEmail: mzaid@nisum.com
 *Summary : Screenshot utils Page
 *CreatedDate : Oct-11-2023
 */


public class TestDataProvider {
    public static final String EMPTY_STRING = "";
    private static final Logger logger = Logger.getLogger(String.valueOf(TestDataProvider.class));
    public static Properties property = new PropertyLoader().getProperty();

    public enum TestData {
        HOME_URL("HOME_URL");
        private final String value;

        TestData(String value) {
            this.value = value;
        }

        public static String val(String value) {
            try {
                return property.getProperty(value);
            } catch (Exception e) {
                System.out.println(value + " is not present in the test properties file");
                return null;
            }
        }

        public String val() {
            try {
                return property.getProperty(value);
            } catch (Exception e) {
                System.out.println(value + " is not present in the test properties file");
                return null;
            }
        }
    }
}