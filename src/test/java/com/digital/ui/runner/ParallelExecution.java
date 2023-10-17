//package com.digital.ui.runner;
//import courgette.api.CourgetteOptions;
//import courgette.api.CourgetteRunLevel;
//import courgette.api.junit.Courgette;
//import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
//
//
///**
// *AuthorName: Murali Krishnan Nagaraj
// *AuthorEmail: muralikrishnan.nagaraj@albertsons.com
// *Summary : Cucumber Runner - Parallel Execution
// *CreatedDate : March-26-2019
// */
//
//
//@RunWith(Courgette.class)
//@CourgetteOptions(
//        threads = 3,
//        runLevel = CourgetteRunLevel.SCENARIO,
//        rerunFailedScenarios = false,
//        rerunAttempts = 1,
//        showTestOutput = true,
//        reportTargetDir = "target",
//        cucumberOptions =
//        @CucumberOptions(
//                features = "src/test/resources/ui/features/groupclinicfeatures",
//                glue = "com.digital.ui.steps",
//                tags = "@DOB1",
//                plugin = {
//                        "pretty",
//                        "json:target/cucumber-report/cucumber.json",
//                        "html:target/cucumber-report/cucumber.html",
//                        "junit:target/cucumber-report/cucumber.xml"
//                }))
//                //},
//                //strict = true))
//public class ParallelExecution {
//}