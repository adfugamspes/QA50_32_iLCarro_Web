package manager;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.enums.HeaderMenuItem;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static utils.UserFactory.positiveUserLogin;

public class AppManager {
    public final static Logger logger = LoggerFactory.getLogger(AppManager.class);

    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    public WebDriver setDriver(WebDriver wd){
        return driver = wd;
    }

    @BeforeMethod
    public void setup(){
        logger.info("Start testing " + LocalDate.now() + " : " + LocalTime.now());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        logger.info("Stop testing " + LocalDate.now() + " : " + LocalTime.now());
        if(driver != null)
            driver.quit();
    }
}
