package com.digital.ui.footer;

import org.openqa.selenium.WebElement;

public class FooterLinkData {
    private final WebElement xPath;
    private final String URL;
    private final String pageText;

    FooterLinkData(WebElement xPath, String URL, String pageText){
        this.xPath = xPath;
        this.URL = URL;
        this.pageText = pageText;
    }

}
