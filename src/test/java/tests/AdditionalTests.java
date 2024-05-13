package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class AdditionalTests {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testScooterLogoClick() { //Проверка если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
        mainPage.getPageHeader().click();
        assertTrue(driver.getCurrentUrl().equals("https://qa-scooter.praktikum-services.ru/"));
    }

    @Test
    public void testYandexLogoClick() { //Проверка если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
        String currentWindow = driver.getWindowHandle();
        driver.findElement(By.className("Header_LogoYandex__3TSOI")).click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
                assertTrue(driver.getCurrentUrl().equals("https://dzen.ru/?yredirect=true"));
                driver.close();
            }
        }
        driver.switchTo().window(currentWindow);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}