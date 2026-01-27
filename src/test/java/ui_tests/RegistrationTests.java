package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;
import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {

    RegistrationPage registrationPage;
    @BeforeMethod
    public void goToSignUpPage(){
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTestWithUser() {
        User user = User.builder().firstName("John").lastName("Smith").
                email(registrationPage.randomEmailGenerator()).password("Password123!").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistration();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
    }

    @Test
    public void registrationPositiveTestWithActions() {
        int i = new Random().nextInt(10000);
        User user = User.builder().firstName("John").lastName("Snow").email("snow"+ i + "@gmail.com").password("Password!123").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
//        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTestWithFaker() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }
}
