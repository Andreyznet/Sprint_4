package tests;

import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;

import java.util.Set;

import static org.junit.Assert.assertTrue;

public class AdditionalTests extends BaseTest {

    private MainPage mainPage;
    private final String yandexUrl = "https://dzen.ru/?yredirect=true"; // URL Яндекса

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
    }

    @Test
    public void testScooterLogoClick() { //Проверка если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
        mainPage.getPageHeader().click();
        assertTrue(driver.getCurrentUrl().equals(baseUrl));
    }

    @Test
    public void testYandexLogoClick() { //Проверка если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
        String currentWindow = driver.getWindowHandle();
        mainPage.getYandexLogo().click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
                assertTrue(driver.getCurrentUrl().equals(yandexUrl));
                driver.close();
            }
        }
        driver.switchTo().window(currentWindow);
    }
}