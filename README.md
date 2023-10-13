# Digital-Automation 

Gradle Commands 

IPhone : ./gradlew chromeSpooferCucumberTest -Ptestdata=pharmacy-qa2 -PcucumberFeature=common/SignInPage.feature -PcucumberTags=@Murali -PrwdDevice='iPhone X'

Chrome Desktop : gradlew chromeCucumberTest -Ptestdata=pharmacy-qa2 -PcucumberFeature=Pharmacy/DOBAndZip.feature -PcucumberTags="@runMe"

Chrome Desktop : ./gradlew chromeCucumberTest -Ptestdata=randalls-qa2 -PcucumberFeature=desktop/SignInPage.feature

IPad : ./gradlew chromeSpooferCucumberTest -Ptestdata=tomthumb-qa2 -PcucumberFeature=common/SignInPage.feature -PcucumberTags=@Murali -PrwdDevice='iPad'

Accessibility: ./gradlew chromeCucumberTest -Ptestdata=safeway-prod -PcucumberTags=@DesktopP1 -Pa11yAnalysisRequired=yes

Local Cucumber Reports:
Reports can also be viewed from <source code location>/ cucumber-local-reports/cucumber-html-reports/ overview-features.html
For more information, refer to https://github.com/SpacialCircumstances/gradle-cucumber-reporting
Note: Local Cucumber Reports will work only when Gradle CLI command is used

For test type tasks, this plugin  will automatically run and try to generate the reports. For normal tasks, we have execute the CLI for generating local cucumber reports. Below is the example command:
Windows: gradlew.bat chromeCucumberTest -Ptestdata=safeway-prod -PcucumberFeature=desktop/SignInPage.feature -PcucumberTags=@SreDesktopP0 && gradlew.bat generateCucumberReports
Linux: ./gradlew chromeCucumberTest -Ptestdata=safeway-prod -PcucumberFeature=desktop/SignInPage.feature -PcucumberTags=@SreDesktopP0 && ./gradlew generateCucumberReports
test
