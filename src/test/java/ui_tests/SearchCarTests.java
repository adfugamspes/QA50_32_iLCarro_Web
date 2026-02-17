package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FindCarSearchResults;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.enums.HeaderMenuItem;

import static utils.UserFactory.*;

public class SearchCarTests extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void goToHomePage(){
        homePage = new HomePage(getDriver());
    }

    @Test
    public void checkElements(){
       homePage.typeFindCarForm("Tel-Aviv", "2/18/2026 - 2/21/2026");
       homePage.activateAndClickBtnYalla();
       FindCarSearchResults findCarSearchResults = new FindCarSearchResults(getDriver());
       Assert.assertTrue(findCarSearchResults.isTextInSearchResultsMessagePresents("No available cars"));
    }

}
