package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;

import static utils.UserFactory.*;

public class RegistrationTests extends AppManager {

    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToSignUpPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest_WithUser() {
        User user = User.builder().firstName("John").lastName("Smith").
                email(registrationPage.randomEmailGenerator()).password("Password123!").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistration_WithJS();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
    }

    @Test
    public void registrationPositiveTest_WithActions() {
        int i = new Random().nextInt(10000);
        User user = User.builder().firstName("John").lastName("Snow").email("snow" + i + "@gmail.com").password("Password!123").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
//        Assert.assertTrue(registrationPage.isPopUpRegistrationDisplayed());
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUserRegistration();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists() {
        User user = positiveUserRegistration();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        PopUpPage popUpPage = new PopUpPage(getDriver());
        popUpPage.clickBtnOk();
        registrationPage.clickBtnLogout();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnSignUp();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(popUpPage.isTextInPopUpMessagePresent("User already exists"));
    }

    @Test
    public void registrationNegativeTest_SpaceInFirstNameField_BUG() {
        User user = User.builder().firstName(" ").lastName("Brown").email(registrationPage.randomEmailGenerator()).password("Password!123").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("must not be blank"));
    }
    @Test
    public void registrationNegativeTest_WithAllEmptyFields() {
        User user = User.builder().firstName("")
                .lastName("").email("").password("").build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        registrationPage.clickBtnYallaReg();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"), "validate error message for Name field");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"), "validate error message for Last Name field");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"), "validate error message for Email field");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"), "validate error message for Password field");
        softAssert.assertAll();
    }


    //===========================HW7==============================

    @Test
    public void registrationNegativeTest_WOCheckBox(){
        User user = positiveUserRegistration();
        registrationPage.typeRegistrationForm(user);
        Assert.assertFalse(registrationPage.isBtnYallaRegEnabled());
    }

    @Test
    public void registrationNegativeTest_BlankFirstName(){
        User user = positiveUserRegistration();
        user.setFirstName("");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_SpaceInFirstName(){
        User user = positiveUserRegistration();
        user.setFirstName(" ");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isBtnYallaRegEnabled());
        registrationPage.clickBtnYallaReg();
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("must not be blank"));
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_BlankLastName(){
        User user = positiveUserRegistration();
        user.setLastName("");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_SpaceInLastName(){
        User user = positiveUserRegistration();
        user.setLastName(" ");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isBtnYallaRegEnabled());
        registrationPage.clickBtnYallaReg();
        softAssert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("must not be blank"));
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_BlankEmail(){
        User user = positiveUserRegistration();
        user.setEmail("");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_SpaceInEmail(){
        User user = positiveUserRegistration();
        user.setEmail(" ");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_BlankPassword(){
        User user = positiveUserRegistration();
        user.setPassword("");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_SpaceInPassword(){
        User user = positiveUserRegistration();
        user.setPassword(" ");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_PasswordWOUpperCase(){
        User user = positiveUserRegistration();
        user.setPassword("qwerty^35");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_PasswordWOLowerCase(){
        User user = positiveUserRegistration();
        user.setPassword("QWERTY^35");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_PasswordWODigits(){
        User user = positiveUserRegistration();
        user.setPassword("Qwerty^ok");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_PasswordWOSpecialChar(){
        User user = positiveUserRegistration();
        user.setPassword("Qwerty.35");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_Password7Symbols(){
        User user = positiveUserRegistration();
        user.setPassword("Qrty^35");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWOAtSymbol(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail123gmail.com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailDoubleAtSymbol(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail@@123gmail.com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWODot(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail123@gmailcom");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWithDoubleDot(){
        User user = positiveUserRegistration();
        user.setEmail("testing..mail123@gmail.com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWOLettersAfterAtSymbol(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail123@");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWOLettersBeforeAtSymbol(){
        User user = positiveUserRegistration();
        user.setEmail("@gmailcom");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailDoubleDotInDomain(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail123@gmail..com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWOLocalDomain(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail123@.com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWOTopDomain(){
        User user = positiveUserRegistration();
        user.setEmail("testingmail123@gmail");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWithSpecialChar_BUG(){
        User user = positiveUserRegistration();
        user.setEmail("testing$mail123@gmail.com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_EmailWithSpace(){
        User user = positiveUserRegistration();
        user.setEmail("testing mail123@gmail.com");
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxRegistrationWithActions();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertFalse(registrationPage.isBtnYallaRegEnabled());
        softAssert.assertAll();
    }


}
