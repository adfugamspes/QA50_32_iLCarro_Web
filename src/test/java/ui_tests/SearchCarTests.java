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
        homePage.clickBtnYalla_WithClickWait();
        Assert.assertTrue(homePage.isUrlContains("results", 3));
    }

    @Test(expectedExceptions = org.openqa.selenium.TimeoutException.class)
    public void searchCarNegativeTest_EmptyFieldCity(){
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 21);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla_WithClickWait();
    }

    @Test
    public void searchCarNegativeTest_EmptyFieldCityValidateError(){
        String city = "";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 21);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        Assert.assertTrue(homePage.isTextInErrorPresent("City is required"));
    }


    //===============================HW10========================
    @Test
    public void searchCarNegativeTest_SameDate_ValidateError(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 12);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"));
    }

    @Test
    public void searchCarNegativeTest_WrongDatesOrder_ValidateError(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2026, 4, 1);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("Second date must be after first date"));
    }

    @Test
    public void searchCarNegativeTest_PastDates_ValidateError(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.of(2025, 4, 12);
        LocalDate endDate = LocalDate.of(2025, 4, 22);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"));
    }

    @Test
    public void searchCarNegativeTest_LongPeriodSelection_ValidateError(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.of(2026, 4, 12);
        LocalDate endDate = LocalDate.of(2027, 4, 22);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date after one year"));
    }

    @Test
    public void searchCarNegativeTest_WrongMonth_ValidateError(){
        homePage.typeFindCarForm("Haifa", "13/02/2027 - 12/10/2027");
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.isTextInErrorPresent("Dates are required"));
    }

    @Test
    public void searchCarPositiveTest_WithCalendar() {
        String city = "Tel-Aviv";
        LocalDate startDate = LocalDate.of(2026, 10, 12);
        LocalDate endDate = LocalDate.of(2026, 11, 21);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.activateAndClickBtnYalla();
        Assert.assertTrue(homePage.isUrlContains("results", 3));
    }


}
