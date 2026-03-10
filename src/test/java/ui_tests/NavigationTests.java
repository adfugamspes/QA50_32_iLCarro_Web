package ui_tests;

import manager.AppManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.enums.FooterMenuItem;
import utils.enums.HeaderMenuItem;

public class NavigationTests extends AppManager {

    SoftAssert softAssert = new SoftAssert();
    HomePage homePage;

    @Test(groups = {"regression", "footer"})
    public void iconFacebookNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconFooter(FooterMenuItem.ICON_FACEBOOK, "Facebook"));
        softAssert.assertTrue(homePage.isUrlContains("facebook", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "footer"})
    public void iconTelegramNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconFooter(FooterMenuItem.ICON_TELEGRAM, "Telegram"));
        softAssert.assertTrue(homePage.isUrlContains("telegram", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "footer"})
    public void iconVKNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconFooter(FooterMenuItem.ICON_VK, "VK"));
        softAssert.assertTrue(homePage.isUrlContains("vk", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "footer"})
    public void iconInstagramNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconFooter(FooterMenuItem.ICON_INSTAGRAM, "Instagram"));
        softAssert.assertTrue(homePage.isUrlContains("instagram", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "footer"})
    public void iconSlackNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconFooter(FooterMenuItem.ICON_SLACK, "Slack"));
        softAssert.assertTrue(homePage.isUrlContains("slack", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "footer"})
    public void footerButtonsNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        homePage.clickLogoFooter();
        softAssert.assertTrue(homePage.isUrlContains("search", 3), "btn logo");
        homePage.clickBtnSearchFooter();
        softAssert.assertTrue(homePage.isUrlContains("search", 3), "btn search");
        homePage.clickBtnLetTheCarWorkFooter();
        softAssert.assertTrue(homePage.isUrlContains("let-car-work", 3), "btn let the car work");
        homePage.clickBtnTermsOfUseFooter();
        softAssert.assertTrue(homePage.isUrlContains("terms-of-use", 3), "btn terms of use");
        homePage.clickBtnRegistrationFooter();
        softAssert.assertTrue(homePage.isUrlContains("registration", 3), "btn registration");
        homePage.clickBtnLogInFooter();
        softAssert.assertTrue(homePage.isUrlContains("login", 3), "btn login");
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "header"})
    public void btnSearchHeaderNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconHeader(HeaderMenuItem.SEARCH, "Search"));
        softAssert.assertTrue(homePage.isUrlContains("search", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "header"})
    public void btnLetTheCarWorkHeaderNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconHeader(HeaderMenuItem.LET_THE_CAR_WORK, "Let the car work!"));
        softAssert.assertTrue(homePage.isUrlContains("let-car-work", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "header"})
    public void btnTermsOfUseHeaderNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconHeader(HeaderMenuItem.TERMS_OF_USE, "Terms of use"));
        softAssert.assertTrue(homePage.isUrlContains("terms-of-use", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "header"})
    public void btnRegistrationHeaderNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconHeader(HeaderMenuItem.SIGN_UP, "Registration"));
        softAssert.assertTrue(homePage.isUrlContains("registration", 5));
        softAssert.assertAll();
    }

    @Test(groups = {"regression", "header"})
    public void btnLoginHeaderNavigationPositiveTest() {
        homePage = new HomePage(getDriver());
        softAssert.assertTrue(homePage.clickIconHeader(HeaderMenuItem.LOGIN, "Login"));
        softAssert.assertTrue(homePage.isUrlContains("login", 5));
        softAssert.assertAll();
    }

}
