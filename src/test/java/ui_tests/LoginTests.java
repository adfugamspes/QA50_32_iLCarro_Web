package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import org.testng.asserts.SoftAssert;

public class LoginTests extends AppManager {

    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLogInPage(){
        new HomePage(getDriver()).clickBtnLogIn();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTestWithUser (){
        User user = User.builder().email("correctmail123@mail.com").password("Password123!").build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }

    @Test
    public void loginPositiveTest_WithPopUpPage (){
        User user = User.builder().email("correctmail123@mail.com").password("Password123!").build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecialChar(){
        User user = User.builder().email("correctmail123@mail.com").password("Password123").build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_InvalidEmailAndBlankPassword(){
        User user = User.builder().email("correctmail123mail.com").password("").build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"), "valid email field");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "valid password field");
        softAssert.assertAll();
    }

}
