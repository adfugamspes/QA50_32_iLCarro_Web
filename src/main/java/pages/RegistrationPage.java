package pages;

import dto.User;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage (WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "name")
    WebElement inputFirstName;

    @FindBy(id = "lastName")
    WebElement inputSecondName;

    @FindBy(id = "email")
    WebElement inputEmailReg;

    @FindBy(id = "password")
    WebElement inputPasswordReg;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYallaReg;

//    @FindBy(xpath = "//input[@type='checkbox']")
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxRegistration;

    @FindBy(xpath = "//h1[text()='Registered']")
    WebElement popUpSuccessfulRegistration;

    public void typeRegistrationForm(User user){
        inputFirstName.sendKeys(user.getFirstName());
        inputSecondName.sendKeys(user.getLastName());
        inputEmailReg.sendKeys(user.getEmail());
        inputPasswordReg.sendKeys(user.getPassword());
    }

    public void clickCheckBoxRegistration(){
//        checkBoxRegistration.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkBoxRegistration);
    }

    public void clickBtnYallaReg(){
        btnYallaReg.click();
    }

    public boolean isPopUpRegistrationDisplayed(){
        return isElementDisplayed(popUpSuccessfulRegistration);
    }

    public void clickCheckBoxRegistrationWithActions(){
        int y = checkBoxRegistration.getSize().getHeight();
        int x = checkBoxRegistration.getSize().getWidth();
        System.out.println(x + "x" + y + "y");
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBoxRegistration, -x/2, -y/2).click().perform();
    }
}
