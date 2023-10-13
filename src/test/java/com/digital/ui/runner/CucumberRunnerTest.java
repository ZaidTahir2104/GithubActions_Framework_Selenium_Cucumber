package com.digital.ui.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/ui/features/myteamsfeaturesQA",
		glue = "src/test/java/com/digital/ui/steps/myteamssteps",
		plugin = {"pretty", "html:target/cucumber-report"},
		monochrome = true
)

/**
 *AuthorName: Muhammad Zaid Tahir
 *AuthorEmail: mzaid@nisum.com
 *Summary : Screenshot utils Page
 *CreatedDate : Oct-11-2023
 */

public class CucumberRunnerTest {}
