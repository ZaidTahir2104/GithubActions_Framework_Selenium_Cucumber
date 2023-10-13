package com.digital.ui.steps.myteamssteps;

import com.digital.ui.pages.myteamspages.MyTeamsHomePage;
import com.digital.ui.steps.BaseBrowser;
import com.digital.util.CommonUtilities;
import com.digital.util.TestDataProvider;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class MyTeamsHomePageSteps {
    public WebDriver driver;
    public MyTeamsHomePage myTeamsHomePage;

    public MyTeamsHomePageSteps(BaseBrowser baseBrowser) {
        this.driver = baseBrowser.webDriver;
        myTeamsHomePage = new MyTeamsHomePage(this.driver);
    }

    @When("User is able to see google image logo at Google Home Page")
    public void userisAbleToSeeGoogleImageLogoAtGoogleHomePage() {
        myTeamsHomePage.googleImageLogo();
    }
    @When("User enters text {string} into Search field at Google Home Page and clicks Enter")
    public void userEntersTextIntoSearchField(String text) {
        myTeamsHomePage.searchText(text);
        CommonUtilities.pressEnter(driver);
    }

    @Given("^User navigates to Google HomePage$")
    public void the_user_opens_vaccination_page_Prod() throws Throwable {
        navigateToUrl(TestDataProvider.TestData.HOME_URL.val()); //we navigate to base URI
        CommonUtilities.waitForThePageToLoad(driver, 10);
    }

    /** * Navigates to any given URL
     * @param url - The URL that needs to be navigated
     */
    private void navigateToUrl(String url){
        System.out.println("Navigate to: " + url);
        driver.navigate().to(url);
    }

    @Then("Verify page navigation to Vaccine Selection")
    public void verifyNavigatedPageIsServiceSelectionPage(){
        Assert.assertEquals(driver.getCurrentUrl(), TestDataProvider.TestData.HOME_URL.val());
    }

}