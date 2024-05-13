package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");// Поле "Имя"
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");// Поле "Фамилия"
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");// Поле "Адрес"
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");// Поле "Станция метро"
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");// Поле "Телефон"
    //private final By nextButton = By.xpath(".//button[text()='Далее']");// Кнопка "Далее"
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");// Кнопка "Далее"
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // Поле когда привезти самокат
    private final By rentalPeriodField = By.xpath(".//div[text()='* Срок аренды']"); // Поле срок аренды
    private final By colorScooterBlack = By.xpath(".//input[@id='black']"); // Цвет самоката черный
    private final By colorScooterGrey = By.xpath(".//input[@id='grey']"); // Цвет самоката серый
    private final By commetField = By.xpath(".//input[@placeholder='Комментарий для курьера']");// Поле комментарий для курьера
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");// Кнопка "Заказать"
    private final By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");// Кнопка "Да"

    private final By successPopup = By.xpath(".//div[text()='Заказ оформлен']");// Всплывающее окно об успешном создании заказа
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
    }
    public void enterNameField(String name){ // Заполняем поле Имя
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    public void enterSurnameField(String surname){// Заполняем поле Фамилия
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }
    public void enterAddressField(String address){// Заполняем поле адрес
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }
    public void enterMetroStationField(String metroStation){// Выбираем станцию метро
        driver.findElement(metroStationField).clear();
        driver.findElement(metroStationField).sendKeys(metroStation);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='"+ metroStation +"']")));
        driver.findElement(By.xpath(".//div[text()='"+ metroStation +"']")).click();
    }
    public void enterPhoneField(String phone){// Заолняем поле телефон
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }
    public void clickNextButton(){// Нажимаем кнопку далее
        driver.findElement(nextButton).click();
    }
    public void enterValideOrderDate(){ // Заполняем поле даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String validDate = LocalDate.now().plusDays(1).format(formatter);
        driver.findElement(dateField).clear();
        driver.findElement(dateField).sendKeys(validDate);
        driver.findElement(dateField).sendKeys(Keys.ENTER);
    }
    public void enterRentalPeriod(int period){// Метод выбора срока аренды
        driver.findElement(rentalPeriodField).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Dropdown-menu")));
        switch (period){
            case 1: driver.findElement(By.xpath(".//div[text()='сутки']")).click(); break;
            case 2: driver.findElement(By.xpath(".//div[text()='двое суток']")).click(); break;
            case 3: driver.findElement(By.xpath(".//div[text()='трое суток']")).click(); break;
            case 4: driver.findElement(By.xpath(".//div[text()='четверо суток']")).click(); break;
            case 5: driver.findElement(By.xpath(".//div[text()='пятеро суток']")).click(); break;
            case 6: driver.findElement(By.xpath(".//div[text()='шестеро суток']")).click(); break;
            case 7: driver.findElement(By.xpath(".//div[text()='семеро суток']")).click(); break;
        }
    }
    public void checkedColorScooterBlack(Boolean isBlack){// Чекаем черный цвет
        if (!driver.findElement(colorScooterBlack).isSelected() & isBlack) {
            driver.findElement(colorScooterBlack).click();
        }
    }
    public void checkedColorScooterGrey(Boolean isGrey){// Чекаем серый цвет
        if (!driver.findElement(colorScooterGrey).isSelected() & isGrey) {
            driver.findElement(colorScooterGrey).click();
        }
    }
    public void enterComment(String comment){// Заполняем поле коммментарий
        driver.findElement(commetField).clear();
        driver.findElement(commetField).sendKeys(comment);
    }
    public void clickOrderButton(){// Кликаем по кнопке заказать
        driver.findElement(orderButton).click();
    }
    public void clickYesButton(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3"))); // Ждем появления формы подтверждения заказа, только после этого жмем кнопку Да
        driver.findElement(yesButton).click();
    }
    public void fillForm(String name, String surname, String address, String metroStation, String phone, int period, Boolean isBlack, Boolean isGrey, String comment) {
        enterNameField(name);
        enterSurnameField(surname);
        enterAddressField(address);
        enterMetroStationField(metroStation);
        enterPhoneField(phone);
        clickNextButton();
        enterValideOrderDate();
        enterRentalPeriod(period);
        checkedColorScooterBlack(isBlack);
        checkedColorScooterGrey(isGrey);
        enterComment(comment);
        clickOrderButton();
        clickYesButton();
    }

    public WebElement getSuccessPopup() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successPopup));
    }
}