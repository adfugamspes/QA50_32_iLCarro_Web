package ui_tests;

import dto.Car;
import dto.User;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.CarFactory;
import utils.PropertiesReader;
import utils.enums.HeaderMenuItem;
import utils.PropertiesReader.*;
import static utils.UserFactory.*;

import static pages.BasePage.clickButtonHeader;
import static utils.PropertiesReader.getProperty;
import static pages.BasePage.clickWait;

public class LetTheCarWorkTests extends AppManager {

    HomePage homePage;
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(positiveUserLogin());
        loginPage.clickBtnYalla();
        new PopUpPage(getDriver()).clickBtnOk();
        letTheCarWorkPage = clickButtonHeader(HeaderMenuItem.LET_THE_CAR_WORK);
    }

    @Test
    public void addNewCarPositiveTest_AllFields(){
        letTheCarWorkPage.typeCarForm(CarFactory.positiveCar());
        letTheCarWorkPage.activateAndClickBtnSubmit();
    }
}
