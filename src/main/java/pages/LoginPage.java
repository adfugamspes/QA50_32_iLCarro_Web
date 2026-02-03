package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import javax.xml.xpath.XPath;

import static pages.BasePage.setDriver;

public class LoginPage extends BasePage{
    public LoginPage (WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYallaLog;

    @FindBy(xpath = "//h2[text()='Logged in success']")
    WebElement popUpSuccessfulLogin;

    public void typeLoginForm(User user){
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla(){
        btnYallaLog.click();
    }

    public boolean isLoggedInDisplayed(){
        return isElementDisplayed(popUpSuccessfulLogin);
    }

    public boolean isBtnYallaLogEnabled(){
        return btnYallaLog.isEnabled();
    }
}
