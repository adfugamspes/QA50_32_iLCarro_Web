package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FindCarSearchResults;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;

import static utils.UserFactory.*;

public class SearchCarTests extends AppManager {
    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLogInPage(){
        homePage = new HomePage(getDriver());
        homePage.clickBtnLogIn();
        loginPage = new LoginPage(getDriver());
        User user = positiveUserLogin();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        new PopUpPage(getDriver()).clickBtnOk();
    }

    @Test
    public void checkElements(){
       new HomePage(getDriver()).typeFindCarForm("Tel-Aviv", "2/18/2026 - 2/21/2026");
       homePage.activateAndClickBtnYalla();
       FindCarSearchResults findCarSearchResults = new FindCarSearchResults(getDriver());
       Assert.assertTrue(findCarSearchResults.isTextInSearchResultsMessagePresents("No available cars"));
    }


}
