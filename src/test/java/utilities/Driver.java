package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

    public class Driver {

        //private constructor ,to create object only in this class
        private Driver(){

        }
        //private driver
        private static WebDriver driver;


        //if my driver fully fresh untouched
        //get driver and quit driver method
        public static WebDriver getDriver(){
            if(driver == null) {
                // Telling your system where your chrome driver is located
                //System.setProperty("webdriver.chrome.driver", "/Users/techglobal/IdeaProjects/selenium_intro/chromedriver");


                //The browser defined below with String is hard-coded
                //       String browser = "chrome"; // define which browser you will run your test in

                switch (ConfigReader.getProperty("browser")) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "safari":
                        WebDriverManager.getInstance(SafariDriver.class).setup();
                        driver = new SafariDriver();
                        break;
                    case "headless":
                        driver = new HtmlUnitDriver();
                        break;
                    default:
                        throw new NotFoundException("Browser IS NOT DEFINED properly!!!");
                }
                if (!ConfigReader.getProperty("browser").equals("headless")) {
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitWait")), TimeUnit.SECONDS);
                }
            }
            return driver;
        }


        public static void quitDriver() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null; //this line is SUPER IMPORTANT
            }
        }

    }

