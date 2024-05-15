package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://qa-scooter.praktikum-services.ru/"; // URL сайта
    @Before
    public void setUp() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
        driver.get(baseUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
