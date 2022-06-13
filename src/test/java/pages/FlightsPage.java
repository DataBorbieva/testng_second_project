package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightsPage {
    public FlightsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='app-components-Shopping-TripInfo-styles__tripOriginDestinationHeader--RcPyp']")
    public WebElement departureInfo;
}

