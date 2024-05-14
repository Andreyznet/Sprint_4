package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private MainPage mainPage;
    private OrderPage orderPage;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private int period;
    private Boolean isBlack;
    private Boolean isGrey;
    private String comment;

    public OrderTest(String name, String surname, String address, String metroStation, String phone, int period, Boolean isBlack, Boolean isGrey, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.period = period;
        this.isBlack = isBlack;
        this.isGrey = isGrey;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Пушкина, д. 1", "Черкизовская", "+79001234567", 3, true, false, "Нет домофона"},
                {"Петр", "Петров", "пр. Невский, д. 10", "Сокольники", "+79007654321", 1, false, true, "Есть домофон"}
        });
    }

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrderFromTopButton() {
        mainPage.getOrderButtonTop().click();
        orderPage.fillForm(name, surname, address, metroStation, phone, period, isBlack, isGrey, comment);
        assertTrue("Форма 'Заказ оформлен' не появилась",!orderPage.isSuccessPopup());
    }

    @Test
    public void testOrderFromBottomButton() {
        mainPage.getOrderButtonBottom().click();
        orderPage.fillForm(name, surname, address, metroStation, phone, period, isBlack, isGrey, comment);
        assertTrue("Форма 'Заказ оформлен' не появилась", !orderPage.isSuccessPopup());
    }
}