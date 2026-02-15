package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static utils.PropertiesReader.getProperty;

public class FindCarSearchResults extends BasePage{
    public FindCarSearchResults (WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "city")
    WebElement inputCity;

    @FindBy(xpath = "//input[@formcontrolname='dates']")
    WebElement inputDates;

    @FindBy(xpath = "//button[text()='Search']")
    WebElement btnSearch;

    @FindBy(xpath = "//h3[@class='no-cars-label ng-star-inserted']")
    WebElement searchResultsMessage;

    public void clickBtnSearch(){
        btnSearch.click();
    }

    public boolean isTextInSearchResultsMessagePresents(String text){
        return isTextInElementPresentWait(searchResultsMessage, text);
    }
}
