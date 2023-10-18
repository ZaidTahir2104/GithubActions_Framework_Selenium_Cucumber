Feature: Sample Feature to Open Google home page

  Background: Open Browser and navigate to Google Home page
    Given User navigates to Google HomePage

@UnifiedFlowRegression   @DOB1
  Scenario: Verify Google image Logo is displayed at google Home Page

    When User is able to see google image logo at Google Home Page

#  @UnifiedFlowRegression  @DOB
 # Scenario: Verify when the mandatory fields are entered, Continue button is enabled
#
 #   Given User navigates to b2c Vaccine Screen for 20 years and Zipcode '94568'
  #  Then Verify page navigation to Vaccine Selection

  @UnifiedFlowRegression  @DOB1 @test
  Scenario Outline: Verify User is able to search after enter text in search field

    When User enters text <text> into Search field at Google Home Page and clicks Enter

    Examples:
      |text         |
      |'Nisum'      |
      |'Lahore'     |
      |'Islamabad'  |
      |'Karachi'    |
