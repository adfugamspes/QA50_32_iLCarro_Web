package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;

import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {

    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToSignUpPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest_WithUser() {
        User user = User.builder().firstName("John").lastName("Smith").
                email(registrationPage.randomEmailGenerator()).password("Password123!").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistration_WithJS();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
    }

    @Test
    public void registrationPositiveTest_WithActions() {
        int i = new Random().nextInt(10000);
        User user = User.builder().firstName("John").lastName("Snow").email("snow" + i + "@gmail.com").password("Password!123").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
//        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUserRegistration();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists() {
        User user = positiveUserRegistration();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        PopUpPage popUpPage = new PopUpPage(getDriver());
        popUpPage.clickBtnOk();
        registrationPage.clickBtnLogout();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnSignUp();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(popUpPage.isTextInPopUpMessagePresent("User already exists"));
    }

    @Test
    public void registrationNegativeTest_SpaceInFirstNameField_BUG() {
        User user = User.builder().firstName(" ").lastName("Brown").email(registrationPage.randomEmailGenerator()).password("Password!123").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("must not be blank"));
    }
    @Test
    public void registrationNegativeTest_WithAllEmptyFields() {
        User user = User.builder().firstName("")
                .lastName("").email("").password("").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"), "validate error message for Name field");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"), "validate error message for Last Name field");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"), "validate error message for Email field");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"), "validate error message for Password field");
        softAssert.assertAll();
    }
}
