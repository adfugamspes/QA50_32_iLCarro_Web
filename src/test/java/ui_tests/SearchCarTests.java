package ui_tests;

import dto.User;
import manager.AppManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
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

import java.time.LocalDate;

import static utils.UserFactory.*;

public class SearchCarTests extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage(){
        homePage = new HomePage(getDriver());
    }

    @Test
    public void checkElements(){
       homePage.typeFindCarForm("Tel-Aviv", "2/18/2026 - 2/21/2026");
       homePage.activateAndClickBtnYalla();
       FindCarSearchResults findCarSearchResults = new FindCarSearchResults(getDriver());
       Assert.assertTrue(findCarSearchResults.isTextInSearchResultsMessagePresents("No available cars"));
    }

    //===========================CW12==========================
    @Test
    public void searchCarPositiveTest(){
        String city = "Tel-Aviv";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 21);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isUrlContains("results", 3));
    }

    @Test(expectedExceptions = org.openqa.selenium.TimeoutException.class)
    public void searchCarNegativeTest_EmptyFieldCity(){
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 21);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
    }

    @Test
    public void searchCarNegativeTest_EmptyFieldCityValidateError(){
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 21);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        Assert.assertTrue(homePage.isTextInErrorPresent("City is required"));
    }

    @Test
    public void searchCarPositiveTest_WithDatePicker() {
        String city = "Tel-Aviv";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 21);
        homePage.typeSearchFormCalendar(city, startDate, endDate);
//        homePage.clickBtnYalla();
//        Assert.assertTrue(homePage.isUrlContains("results", 3));
    }
}
