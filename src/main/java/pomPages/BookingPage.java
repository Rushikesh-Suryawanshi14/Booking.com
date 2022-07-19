package pomPages;

import dataProvider.ExcelData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.BaseUtilities;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BookingPage extends BaseUtilities {
    WebDriver driver;
    static ExcelData excelData;

    private By regions = By.xpath("//*[contains(@class,'bui-tab bui-tab')]/div[@data-tab-id='0']//li/span");
    private By checkInOut = By.xpath("//div[@class='xp__dates xp__group']");
    private By dates = By.xpath("//td[@class='bui-calendar__date']");
    private By guest = By.xpath("//div[@class='xp__input-group xp__guests']");

    private By numAdult = By.xpath("//div[contains(@class,'adults')]//span[@class='bui-stepper__display']");
    private By addAdult = By.xpath("//div[contains(@class,'adults')]//button[contains(@class,'add')]");
    private By minAdult = By.xpath("//div[contains(@class,'adults')]//button[contains(@class,'subtract')]");

    private By numChild = By.xpath("//div[contains(@class,'children')]//span[@class='bui-stepper__display']");
    private By addchild = By.xpath("//div[contains(@class,'children')]//button[contains(@class,'add')]");
    private By minChild = By.xpath("//div[contains(@class,'children')]//button[contains(@class,'subtract')]");

    private By numRoom = By.xpath("//div[contains(@class,'room')]//span[@class='bui-stepper__display']");
    private By addRoom = By.xpath("//div[contains(@class,'room')]//button[contains(@class,'add')]");
    private By minRoom = By.xpath("//div[contains(@class,'room')]//button[contains(@class,'subtract')]");
    private By searchbtn = By.xpath("//button[@type='submit']");

    private By fiveStar = By.xpath("(//div[@data-filters-group='class'][1]//span[@class='bbdb949247'])[5]");
    private By hotelRating = By.xpath("//div[@class='b5cd09854e d10a6220b4']");

    private By apartment=By.xpath("//select[@class='hprt-nos-select js-hprt-nos-select']");
    private By reserveBtn=By.xpath("(//button[@type='submit'])[3]");

    private By firstName=By.xpath("//input[@id='firstname']");
    private By lastName=By.xpath("//input[@id='lastname']");
    private By emailId=By.xpath("//input[@id='email']");
    private By conformEmailID=By.xpath("//input[@id='email_confirm']");
    private By mainGuest= By.xpath("//span[contains(text(),'main guest')]");
    private By someOneElse=By.xpath("//span[contains(text(),'someone else')]");
    private By guestName=By.xpath("//input[contains(@id,'guest_name')]");
    private By nextFinalDetails=By.xpath("//span[text()=' Next: Final details ']");

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        excelData=new ExcelData();
    }

    public WebElement clickMaxPropertiesRegion() {
        int max = 0;
        WebElement maxele = null;
        List<WebElement> allregions = driver.findElements(regions);
        for (int i = 0; i < allregions.size(); i++) {
            String regionsText = allregions.get(i).getText();
            String[] region = regionsText.split(" ")[0].split(",");
            StringBuffer sb = new StringBuffer();
            for (String temp : region) {
                sb.append(temp);

            }
            regionsText = sb.toString();

            int a = Integer.parseInt(regionsText);
            if (a > max) {
                max = a;
                maxele = allregions.get(i);

            }

        }
        WebElement maxRegion = driver.findElement(By.xpath("//span[text()='" + maxele.getText() + "']//preceding-sibling::a"));
        System.out.println("Finding the maximum property region on the homepage");
        return maxRegion;

    }


    public void selectCheckInOutDate() throws InterruptedException {
        driver.findElement(checkInOut).click();
//        Thread.sleep(3000);
        List<WebElement> getAllDate = driver.findElements(dates);
        scrollAndClick(getAllDate.get(1));
        scrollAndClick(getAllDate.get(2));
        System.out.println("Selecting the Check-In and Check-Out Date");
    }


    public void selectAdults(int adult) {
        driver.findElement(guest).click();
        String adultText = driver.findElement(numAdult).getText();
        int currentAdult = Integer.parseInt(adultText);


        if (adult > currentAdult) {
            int get = adult - currentAdult;
            while (get != 0) {
                driver.findElement(addAdult).click();
                --get;
            }
        } else if (adult < currentAdult) {
            int get = currentAdult - adult;
            while (get != 0) {
                driver.findElement(minAdult).click();
                --get;
            }
        }

        System.out.println("Selecting the number of adults in guest toggle");

    }

    public void selectChild(int child) {
        String childText = driver.findElement(numChild).getText();
        int currentChild = Integer.parseInt(childText);


        if (child > currentChild) {
            int get = child - currentChild;
            while (get != 0) {
                driver.findElement(addchild).click();
                --get;
            }
        } else if (child < currentChild) {
            int get = currentChild - child;
            while (get != 0) {
                driver.findElement(minChild).click();
                --get;
            }
        }
        System.out.println("Selecting the number of child in guest toggle");
    }

    public void selectRoom(int room) {
        String roomText = driver.findElement(numRoom).getText();
        int currentRoom = Integer.parseInt(roomText);

        if (room > currentRoom) {
            int get = room - currentRoom;
            while (get != 0) {
                driver.findElement(addRoom).click();
                --get;
            }
        } else if (room < currentRoom) {
            int get = currentRoom - room;
            while (get != 0) {
                driver.findElement(minRoom).click();
                --get;
            }
        }
        System.out.println("Selecting the number of room in guest toggle");
    }

    public void clickSearchBtn()
    {
        driver.findElement(searchbtn).click();
        System.out.println("clicking on search button");
    }

    public void applyRating() throws InterruptedException {
        WebElement fiveRating=driver.findElement(fiveStar);
//        Thread.sleep(5000);
        scrollAndView(fiveRating);
//        Thread.sleep(5000);
        scrollAndClick(fiveRating);
        System.out.println("Applying the Rating filter as 5 star on hotel listing page");
    }

    public WebElement selectHotel()
    {
        Double maxRating=0.0;
        WebElement maxiRateEle=null;
        List<WebElement> hotelsList=driver.findElements(hotelRating);

        for (int i=0;i < hotelsList.size();i++)
        {
            String ratingText=hotelsList.get(i).getText();
            Double numRate=Double.parseDouble(ratingText);

            if (numRate>maxRating)
            {
                maxRating=numRate;
                maxiRateEle=hotelsList.get(i);
            }
        }
        WebElement maxRateHotel = driver.findElement(By.xpath("//div[text()='"+ maxiRateEle.getText() +"']"));
        System.out.println("Selecting highest rating hotel from hotel listing page");
        return maxRateHotel;
    }

    public void switchToHotelWindow()
    {
        String parent_Window=driver.getWindowHandle();
        Set<String> all_Windows =driver.getWindowHandles();
        Iterator<String> itr=all_Windows.iterator();
        while (itr.hasNext())
        {
            String child_window=itr.next();
            if (!parent_Window.equalsIgnoreCase(child_window))
            {
                driver.switchTo().window(child_window);
            }
        }
        System.out.println("Switching parentWindow to childWindow");
    }

    public void selectApartment() throws InterruptedException {
//        Thread.sleep(5000);
        WebElement selectApartment=driver.findElement(apartment);
        scrollAndView(selectApartment);
        Select sel=new Select(selectApartment);
        List<WebElement> apartmentDDL=sel.getOptions();
        apartmentDDL.get(1).click();

        System.out.println("Selecting the apartment from select an apartment section");
    }

    public void clickOnReserve()
    {
        WebElement clickReserve=driver.findElement(reserveBtn);
        scrollAndClick(clickReserve);
        System.out.println("Reserve the selected hotel");
    }
    public void enterFirstname(String getFirstName)
    {
        driver.findElement(firstName).sendKeys(getFirstName);
        System.out.println("entering firstname");
    }
    public void enterLastname(String getLastName)
    {
        driver.findElement(lastName).sendKeys(getLastName);
        System.out.println("entering lastname");
    }
    public void enterEmailId(String getEmail)
    {
        driver.findElement(emailId).sendKeys(getEmail);
        System.out.println("entering email id");
    }
    public void enterConfirmEmailId(String getConfirmEmail)
    {
        driver.findElement(conformEmailID).sendKeys(getConfirmEmail);
        System.out.println("entering confirm email id");
    }
    public void whoAreYouBookingFor(String whosBooking,String enterGuestname)
    {
        String forMainGuest=driver.findElement(mainGuest).getText();
        String forSomeoneElse=driver.findElement(someOneElse).getText();
        if(forMainGuest.equalsIgnoreCase(whosBooking))
        {
            driver.findElement(mainGuest).click();
        }
        else if (forSomeoneElse.equalsIgnoreCase(whosBooking))
        {
            driver.findElement(someOneElse).click();
            driver.findElement(guestName).sendKeys(enterGuestname);

        }
        System.out.println("selecting Who are you booking for options");
    }

    public void clickOnNextFinalDetailsBtn()
    {
        driver.findElement(nextFinalDetails).click();
        System.out.println("entered next button");
    }

}
