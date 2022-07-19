package stepdefinitions;

import dataProvider.ExcelData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import utility.BaseUtilities;
import java.io.IOException;
import java.time.Duration;

public class BookRoomStepDef extends BaseUtilities {
   static ExcelData excelData=new ExcelData();
    @Before
    public void impliciteWaits()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
    }
    @Given("user open the browser and navigate to url")
    public void user_open_the_browser_and_navigate_to_url() {
        startUp();
    }
    @When("user select maximum properties place")
    public void user_select_maximum_properties_place() {
        WebElement getRegion=bpg.clickMaxPropertiesRegion();
        scrollAndView(getRegion);
        scrollAndClick(getRegion);

    }
    @When("user select checkin and checkout date")
    public void user_select_checkin_and_checkout_date() throws InterruptedException {
        bpg.selectCheckInOutDate();
    }
    @When("user select number of people,room and click on search button")
    public void user_select_number_of_people_room_and_click_on_search_button() throws InterruptedException {
        bpg.selectAdults(excelData.getCellDataNumber(1,0));
        bpg.selectChild(excelData.getCellDataNumber(1,1));
        bpg.selectRoom(excelData.getCellDataNumber(1,2));
        bpg.clickSearchBtn();
    }

    @When("user get a list of hotels and apply filter as five star from rating filter")
    public void user_get_a_list_of_hotels_and_apply_filter_as_five_star_from_rating_filter() throws InterruptedException {
        bpg.applyRating();
        Thread.sleep(3000);
        bpg.selectHotel().click();
    }

    @When("user select maximum rating place")
    public void user_select_maximum_rating_place() {
        bpg.switchToHotelWindow();
    }

    @When("user select the amount and click on reserve")
    public void user_select_the_amount_and_click_on_reserve() throws InterruptedException {
//        Thread.sleep(3000);
            bpg.selectApartment();
//            Thread.sleep(3000);
            bpg.clickOnReserve();
    }

    @When("user fill the required details and click on next button")
    public void user_fill_the_required_details_and_click_on_next_button() {
        bpg.enterFirstname(excelData.getCellDataString(1,3));
        bpg.enterLastname(excelData.getCellDataString(1,4));
        bpg.enterEmailId(excelData.getCellDataString(1,5));
        bpg.enterConfirmEmailId(excelData.getCellDataString(1,6));
        bpg.whoAreYouBookingFor(excelData.getCellDataString(1,7),excelData.getCellDataString(1,8));
        bpg.clickOnNextFinalDetailsBtn();
    }

    @Then("user should get all details and take screenshot")
    public void user_should_get_all_details_and_take_screenshot() throws IOException, InterruptedException {
//        Thread.sleep(3000);
        takesScreenshotOfPage("BookingDetails");
    }

}
