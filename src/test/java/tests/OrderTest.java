package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertTrue;

public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;
    private final By answer = By.xpath(".//div[text()='Заказ оформлен']");// Форма Заказ оформлен
    @Before
    public void setUp() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS));
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testOrderFromTopButton() {
        mainPage.getOrderButtonTop().click();
        orderPage.fillForm("Иван", "Иванов", "ул. Пушкина, д. 1", "Черкизовская", "+79001234567", 3, true, false, "Нет домофона");
        assertTrue("Форма 'Заказ оформлен' не появилась", !driver.findElements(answer).isEmpty());
    }

    @Test
    public void testOrderFromBottomButton() {
        mainPage.getOrderButtonBottom().click();
        orderPage.fillForm("Петр", "Петров", "пр. Невский, д. 10", "Сокольники", "+79007654321", 1, false, true, "Есть домофон");
        assertTrue("Форма 'Заказ оформлен' не появилась", !driver.findElements(answer).isEmpty());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
