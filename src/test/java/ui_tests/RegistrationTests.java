package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

public class RegistrationTests extends AppManager {

    @Test
    public void registrationPositiveTestWithUser() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnSignUp();
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        User user = User.builder().firstName("John").lastName("Smith").
                email(registrationPage.randomEmailGenerator()).password("Password123!").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistration();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());


    }
}
