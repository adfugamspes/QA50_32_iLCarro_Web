package pages;

import dto.Car;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.CarFactory;

import java.io.File;
import java.util.Random;

import static utils.CarFactory.positiveCar;

public class LetTheCarWorkPage extends BasePage {

    public LetTheCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@placeholder='Enter your address']")
    WebElement inputLocation;

    @FindBy(id = "make")
    WebElement inputManufacture;

    @FindBy(id = "model")
    WebElement inputModel;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(id = "fuel")
    WebElement inputFuel;

    @FindBy(id = "seats")
    WebElement inputSeats;

    @FindBy(id = "class")
    WebElement inputCarClass;

    @FindBy(id = "serialNumber")
    WebElement inputRegistrationNumber;

    @FindBy(id = "price")
    WebElement inputPrice;

    @FindBy(id = "about")
    WebElement inputAbout;

    @FindBy(id = "photos")
    WebElement inputPhoto;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement btnSubmit;

    @FindBy(xpath = "//mat-chip[@class='mat-chip mat-focus-indicator mat-primary mat-standard-chip mat-chip-with-trailing-icon']")
    WebElement photoUploadConfirmation;

    public void typeCarForm(Car car){
        clickWait(inputLocation, 3);
        inputLocation.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(Integer.toString(car.getYear()));
        inputFuel.sendKeys(car.getFuel());
        inputSeats.sendKeys(Integer.toString(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputRegistrationNumber.sendKeys(car.getRegistrationNumber());
        inputPrice.sendKeys(Double.toString(car.getPrice()));
        inputAbout.sendKeys(car.getAbout());
        File photo = new File(car.getPhoto());
        inputPhoto.sendKeys(photo.getAbsolutePath());
        clickWait(photoUploadConfirmation, 10);
    }

    public void activateAndClickBtnSubmit(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled');", btnSubmit);
        btnSubmit.click();
    }





}
