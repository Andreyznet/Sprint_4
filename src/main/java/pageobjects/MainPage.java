package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private final By pageHeader = By.className("Header_LogoScooter__3lsAR"); // логотип «Самоката»
    private final By pageYandexLogo = By.className("Header_LogoYandex__3TSOI"); // логотип «Яндекс»
    private final By orderButtonTop = By.xpath(".//button[text()='Заказать']");// Кнопка "Заказать" вверху
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");// Кнопка "Заказать" внизу
    private final By questions = By.className("accordion__button");// Вопросы о важном
    private final By answer = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']");// Ответ на вопрос

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageHeader() {
        return driver.findElement(pageHeader);
    }

    public WebElement getOrderButtonTop() {
        return driver.findElement(orderButtonTop);
    }

    public WebElement getOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
        return element;
    }

    public WebElement getQuestion(int index) {
        WebElement element = driver.findElement(questions);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(questions));
        return driver.findElements(questions).get(index);
    }

    public WebElement getAnswer(int index){
        WebElement element = driver.findElements(answer).get(index);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(answer));
        return driver.findElements(answer).get(index);
    }

    public WebElement getYandexLogo() {
        return driver.findElement(pageYandexLogo);
    }
}