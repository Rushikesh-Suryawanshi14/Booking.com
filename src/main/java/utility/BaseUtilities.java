package utility;

import dataProvider.ExcelData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pomPages.BookingPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseUtilities {
    public static WebDriver driver;
    public BookingPage bpg;

    public void startUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
        bpg=new BookingPage(driver);
    }

    public  void waitForVisibilityOf(WebElement element,int seconds){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void takesScreenshotOfPage(String name) throws IOException {
        TakesScreenshot scr = (TakesScreenshot) driver;
        File file = scr.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(".//screenshot//"+name+".png"));
    }


    public void scrollAndView(WebElement element)
    {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true)",element);
    }

    public void scrollAndClick(WebElement element)
    {
        try {
            JavascriptExecutor js=(JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();",element);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
