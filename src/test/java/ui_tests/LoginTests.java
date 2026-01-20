package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {

    @Test
    public void loginPositiveTest (){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogIn();
    }

    @Test
    public void loginPositiveTestWithUser (){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogIn();
        User user = User.builder().email("correctmail123@mail.com").password("Password123!").build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }
}
