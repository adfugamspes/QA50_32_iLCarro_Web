package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.Random;

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
        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
    }
}
