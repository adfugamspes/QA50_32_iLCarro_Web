package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static pages.BasePage.setDriver;
import static utils.PropertiesReader.*;

public class HomePage extends BasePage{
    public HomePage (WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
        WebElement btnLogIn;

    @FindBy(xpath = "//a[text()=' Sign up ']")
        WebElement btnSignUp;

    @FindBy(id = "city")
    WebElement inputCity;

    @FindBy(id="dates")
    WebElement inputDates;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnChooseYear;

    public void clickBtnLogIn(){
        btnLogIn.click();
    }

    public void clickBtnSignUp(){
        btnSignUp.click();
    }

    public void activateAndClickBtnYalla(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled');", btnYalla);
        btnYalla.click();
    }

    public void typeFindCarForm(String city, String dates){
        inputCity.sendKeys(city);
        inputDates.sendKeys(dates);
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue()
                + "/" + startDate.getDayOfMonth()
                + "/" + startDate.getYear()
                + " - " + endDate.getMonthValue()
                + "/" + endDate.getDayOfMonth()
                + "/" + endDate.getYear();
        inputDates.sendKeys(dates);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute(\"disabled\")\n");
    }

    public void typeSearchFormWOJS(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        String dates = startDate.getMonthValue() + "/" + startDate.getDayOfMonth() + "/" + startDate.getYear()
                + " - " + endDate.getMonthValue() + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
        inputDates.sendKeys(dates);
    }

    public void clickBtnYalla(){
        clickWait(btnYalla, 3);
    }

    private void typeCalendar(LocalDate date){
        btnChooseYear.click();
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement(By.xpath("td[@aria-label='"+year+"']"));
        btnYear.click();
    }

    public void typeSearchFormCalendar(String city, LocalDate startDate, LocalDate endDate){
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);

    }
}
