package scripts;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UnitedAirlinesTest extends Base {

    /*
    Test Case 1: Validate "Main menu" navigation items
Given user is on "https://www.united.com/en/us"
Then user should see “Main menu” navigation items
|BOOK                              |
|MY TRIPS                          |
|TRAVEL INFO            |
|MILEAGEPLUS® PROGRAM|
|DEALS                             |
|HELP                              |
     */

    @Test(priority = 1, description = "TestCase1")
    public void validateBookTravelMenuOptions() {
        driver.get("https://www.united.com/en/us");

        String[] expectedMenuList = {"BOOK", "MY TRIPS", "TRAVEL INFO", "MILEAGEPLUS® PROGRAM", "DEALS", "HELP"};
        for (int i = 0; i < unitedAirlinesMainPage.mainMenuOptions.size(); i++) {
            assertEquals(unitedAirlinesMainPage.mainMenuOptions.get(i).getText(), expectedMenuList[i]);
            assertTrue(unitedAirlinesMainPage.mainMenuOptions.get(i).isDisplayed());
        }
    }



    /*
    Test Case 2: Validate "Book travel menu" navigation items
Given user is on "https://www.united.com/en/us"
Then user should see "Book travel menu" navigation items
|Book             |
|Flight Status|
|Check-in       |
|My trips        |
     */
    @Test(priority = 2, description = "TestCase2")
    public void validateMainMenuOptions() {
        driver.get("https://www.united.com/en/us");
        String[] expectedBookTravelMenu = {"Book", "Flight status", "Check-in", "My trips"};
        for (int i = 0; i < expectedBookTravelMenu.length; i++) {
            assertTrue(unitedAirlinesMainPage.bookTravelMenuOptions.get(i).isDisplayed());
            assertEquals(unitedAirlinesMainPage.bookTravelMenuOptions.get(i).getText(), expectedBookTravelMenu[i]);
        }
    }
    /*
    Test Case 3: Validate "Round-trip" and "One-way" radio buttons
Given user is on "https://www.united.com/en/us"
Then validate "Roundtrip" radio button is displayed, is enabled and is selected
And validate "One-way" radio button is displayed, is enabled but is not selected
When user clicks on "One-way" radio button
Then validate "One-way" radio button is selected while "Roundtrip" radio button is
deselected
     */
        @Test(priority = 3,description = "TestCase3")
        public void testAndValidateRadioButtons() {
            driver.get("https://www.united.com/en/us");
            //(0) - for text
            //(1) - for checking
            //(2) - for clicking
            assertEquals(unitedAirlinesMainPage.roundTripButtonsList.get(0).getText(),"Roundtrip");
            assertEquals(unitedAirlinesMainPage.oneWayButtonsList.get(0).getText(),"One-way");
            assertTrue(unitedAirlinesMainPage.roundTripButtonsList.get(2).isEnabled());
            assertTrue(unitedAirlinesMainPage.oneWayButtonsList.get(2).isEnabled());
            assertTrue(unitedAirlinesMainPage.roundTripButtonsList.get(1).isSelected());
            unitedAirlinesMainPage.oneWayButtonsList.get(2).click();
            assertTrue(unitedAirlinesMainPage.oneWayButtonsList.get(1).isSelected());
            assertTrue(!unitedAirlinesMainPage.roundTripButtonsList.get(1).isSelected());
        }
/*
Test Case 4: Validate "Book with miles" and "Flexible dates" checkboxes
Given user is on "https://www.united.com/en/us"
Then validate "Book with miles" checkbox is displayed, is enabled but is not selected
And validate "Flexible dates" checkbox is displayed, is enabled but is not selected
When user clicks both checkboxes
Then validate both checkboxes are selected
When user clicks on both selected checkboxes again
Then validate both checkboxes are deselected
 */
@Test(priority = 4,description = "TestCase4")
public void validateCheckBoxes() throws InterruptedException {
    driver.get("https://www.united.com/en/us");
    assertTrue(unitedAirlinesMainPage.bookingWithMilesLabel.isDisplayed());
    assertTrue(unitedAirlinesMainPage.flexibleDatesLabel.isDisplayed());

    assertTrue(!unitedAirlinesMainPage.bookWithMilesInput.isSelected()
            &&  unitedAirlinesMainPage.bookWithMilesInput.isEnabled());
    assertTrue(!unitedAirlinesMainPage.flexibleDatesInput.isSelected()
            &&  unitedAirlinesMainPage.flexibleDatesInput.isEnabled());

    unitedAirlinesMainPage.flexibleDatesLabel.click();
    unitedAirlinesMainPage.bookingWithMilesLabel.click();

    Thread.sleep(5000);
    assertTrue(unitedAirlinesMainPage.bookWithMilesInput.isSelected());
    assertTrue(unitedAirlinesMainPage.flexibleDatesInput.isSelected());
}

  /*  Test Case 5: Validate One-way ticket search results "from Chicago, IL, US (ORD) to
    Miami, FL, US (MIA)”
    Given user is on "https://www.united.com/en/us"
    When user selects "One-way" ticket radio button
    And user enters "Chicago, IL, US (ORD)" to from input box
    And user enters "Miami, FL, US (MIA)" to to input box
    And user selects "Jan 30" to the dates input box
    And user selects "2 Adults" from travelers selector
    And user selects "Business or First" from cabin dropdown
    And user clicks on "Find Flights" button
    Then validate departure equals to "Depart: Chicago, IL, US to Miami, FL, US
*/
  @Test(priority = 5,description = "TestCase5")
  public void validateDeparture(){
      driver.get("https://www.united.com/en/us");
      unitedAirlinesMainPage.oneWayButtonsList.get(1).click();

      unitedAirlinesMainPage.fromInputBox.clear();
      unitedAirlinesMainPage.fromInputBox.sendKeys("Chicago, IL, US (ORD)");
      unitedAirlinesMainPage.toInputBox.clear();
      unitedAirlinesMainPage.toInputBox.sendKeys("Miami, FL, US (MIA)");
      unitedAirlinesMainPage.confirmToFly.click();
      unitedAirlinesMainPage.dateOfFly.clear();
      unitedAirlinesMainPage.dateOfFly.sendKeys("Jan 30");
      unitedAirlinesMainPage.numberOfTravelersButton.click();
      unitedAirlinesMainPage.numbersOfTravelersInput.sendKeys("2");
      unitedAirlinesMainPage.typeOfFly.click();
      unitedAirlinesMainPage.businessClass.click();
      unitedAirlinesMainPage.findFlightsButton.click();

      assertEquals(flightsPage.departureInfo.getText(),"Depart: Chicago, IL, US to Miami, FL, US");


  }

}
