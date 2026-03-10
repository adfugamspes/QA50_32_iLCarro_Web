package ui_tests;

import dto.Car;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.CarFactory;
import utils.enums.HeaderMenuItem;
import static utils.UserFactory.*;
import static pages.BasePage.clickButtonHeader;

public class AddNewCarTests extends AppManager {

    HomePage homePage;
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;
    PopUpPage popUpPage;

    @BeforeMethod(alwaysRun = true)
    public void openLetTheCarWorkPage() {
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(positiveUserLogin());
        loginPage.clickBtnYalla();
        popUpPage = new PopUpPage(getDriver());
        popUpPage.clickBtnOk();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
        letTheCarWorkPage = clickButtonHeader(HeaderMenuItem.LET_THE_CAR_WORK);
        letTheCarWorkPage.pause(3);
    }

    @Test(groups = {"smoke", "regression"})
    public void addNewCarPositiveTest_AllFields(){
        Car car = CarFactory.positiveCar();
        letTheCarWorkPage.typeCarForm(car);
        letTheCarWorkPage.typeImage();
        letTheCarWorkPage.pause(5);
        letTheCarWorkPage.activateAndClickBtnSubmit();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("must not be blank"));
    }

}
