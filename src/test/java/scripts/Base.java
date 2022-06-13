package scripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.FlightsPage;
import pages.UnitedAirlinesMainPage;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Base {
    WebDriver driver;
    WebDriverWait wait;
    Wait fluentWait;
    SoftAssert softAssert;
    Actions actions;
    JavascriptExecutor js;
    UnitedAirlinesMainPage unitedAirlinesMainPage;
    FlightsPage flightsPage;




    @BeforeMethod
    public void setUp(){
   driver = Driver.getDriver();
   softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver,15);
        Wait fluentWait = new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(2,TimeUnit.SECONDS).ignoring(Exception.class);
        softAssert = new SoftAssert();
       actions = new Actions(driver);
        js =(JavascriptExecutor) driver;
   unitedAirlinesMainPage = new UnitedAirlinesMainPage(driver);
   flightsPage = new FlightsPage(driver);
    }

    @AfterMethod
    public void teardown(){
        softAssert.assertAll();
        Driver.quitDriver();
    }
}
