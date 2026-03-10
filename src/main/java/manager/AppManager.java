package manager;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.WDListener;
import utils.enums.HeaderMenuItem;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static utils.UserFactory.positiveUserLogin;

public class AppManager {
    public final static Logger logger = LoggerFactory.getLogger(AppManager.class);

    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        logger.info("Start testing " + LocalDate.now() + " : " + LocalTime.now());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverListener webDriverListener = new WDListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
    }

    @AfterMethod(enabled = true, alwaysRun = true)
    public void tearDown() {
        logger.info("Stop testing " + LocalDate.now() + " : " + LocalTime.now());
        if(driver != null)
            driver.quit();
    }
}


