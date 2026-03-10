package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;
import java.util.Random;

public class LetTheCarWorkPage extends BasePage {

    public LetTheCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@class='ng-untouched ng-pristine ng-invalid pac-target-input']")
    WebElement inputLocation;

    @FindBy(id = "make")
    WebElement inputManufacture;

    @FindBy(id = "model")
    WebElement inputModel;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(id = "fuel")
    WebElement selectFuel;

    @FindBy(id = "seats")
    WebElement inputSeats;

    @FindBy(id = "class")
    WebElement inputCarClass;

    @FindBy(id = "serialNumber")
    WebElement inputRegistrationNumber;

    @FindBy(id = "price")
    WebElement inputPricePerDay;

    @FindBy(id = "about")
    WebElement textAreaAbout;

    @FindBy(id = "photos")
    WebElement inputImage;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement btnSubmit;

    private void typeFuel(Fuel fuel){
        selectFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void typeImage(){
        inputImage.sendKeys(new File(carPhotoRandomizer()).getAbsolutePath());
    }

    public void typeCarForm(Car car){
        clickWait(inputLocation, 5);
        inputLocation.sendKeys(car.getCity());
        clickWait(inputManufacture, 5);
        inputManufacture.sendKeys(car.getManufacture());
        clickWait(inputModel, 5);
        inputModel.sendKeys(car.getModel());
        clickWait(inputYear, 5);
        inputYear.sendKeys(car.getYear());
        typeFuel(car.getFuel());
        inputSeats.sendKeys(car.getSeats() + "");
        inputCarClass.sendKeys(car.getCarClass());
        inputRegistrationNumber.sendKeys(car.getSerialNumber());
        inputPricePerDay.sendKeys(Double.toString(car.getPricePerDay()));
        textAreaAbout.sendKeys(car.getAbout());
    }

    private static String carPhotoRandomizer() {
        int carNumber = new Random().nextInt((10 - 1) + 1) + 1;
        return "src/test/resources/car_images/car-" + carNumber+".png";
    }

    public void activateAndClickBtnSubmit(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled');", btnSubmit);
        btnSubmit.click();
    }





}
