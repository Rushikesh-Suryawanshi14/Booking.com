Feature: Automating Booking.com website

  Scenario: Validating the Booking.com Fuctionality
    Given user open the browser and navigate to url
    When user select maximum properties place
    And user select checkin and checkout date
    And user select number of people,room and click on search button
    And user get a list of hotels and apply filter as five star from rating filter
    And user select maximum rating place
    And user select the amount and click on reserve
    And user fill the required details and click on next button
    Then user should get all details and take screenshot
