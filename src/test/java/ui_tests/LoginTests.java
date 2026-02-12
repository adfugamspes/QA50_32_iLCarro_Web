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
import utils.RetryAnalyzer;

import static utils.PropertiesReader.*;
import static utils.UserFactory.*;

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
        User user = positiveUserLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }

    @Test
    public void loginPositiveTest_WithPopUpPage (){
        User user = positiveUserLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecialChar(){
        User user = positiveUserLogin();
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

    //===========================HW7==============================

    @Test
    public void loginNegativeTest_AllFieldsBlank(){
        User user = User.builder().email("").password("").build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaLogEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"));
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_BlankEmail(){
        User user = positiveUserLogin();
        user.setEmail("");
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaLogEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"));
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_SpaceInEmail(){
        User user = positiveUserLogin();
        user.setEmail(" ");
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaLogEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"));
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_BlankPassword(){
        User user = positiveUserLogin();
        user.setPassword("");
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaLogEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_SpaceInPassword(){
        User user = positiveUserLogin();
        user.setPassword(" ");
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isBtnYallaLogEnabled());
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Login or Password incorrect"), "wrong message");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_WrongEmail(){
        User user = positiveUserLogin();
        user.setEmail("testingmail@gmail.com");
        loginPage.typeLoginForm(user);
        softAssert.assertTrue(loginPage.isBtnYallaLogEnabled());
        loginPage.clickBtnYalla();
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Login or Password incorrect"));
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_WrongPassword(){
        User user = positiveUserLogin();
        user.setPassword("Qwerty^123");
        loginPage.typeLoginForm(user);
        softAssert.assertTrue(loginPage.isBtnYallaLogEnabled());
        loginPage.clickBtnYalla();
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Login or Password incorrect"));
        softAssert.assertAll();
    }


    @Test()
    public void loginNegativeTest_EmailWithSpace(){
        User user = positiveUserLogin();
        user.setEmail("correct mail123@mail.com");
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaLogEnabled());
        softAssert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"));
        softAssert.assertAll();
    }

    //==============================HW8===========================

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginNegativeTest_WrongTestForRetryAnalyzer(){
        User user = positiveUserLogin();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertFalse(loginPage.isBtnYallaLogEnabled());
    }

    //==============================CW10===========================

    @Test
    public void loginPositiveTestWith_PropertiesReader (){
        User user = User.builder()
                .email(getProperty("base.properties", "login"))
                .password(getProperty("base.properties", "password")).
                build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }



}
