package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static pages.BasePage.setDriver;

public class HomePage extends BasePage{
    public HomePage (WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
        WebElement btnLogIn;

    @FindBy(xpath = "//a[text()=' Sign up ']")
        WebElement btnSignUp;

    public void clickBtnLogIn(){
        btnLogIn.click();
    }

    public void clickBtnSignUp(){
        btnSignUp.click();
    }
}
