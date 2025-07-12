package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    // Кнопки "Заказать"
    private final By topOrderButton = By.className("Button_Button__ra12g");
    private final By bottomOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");

    // FAQ
    private final By faqQuestion = By.xpath("//div[@class='accordion__button']");
    private final By faqAnswer = By.xpath("//div[@class='accordion__panel']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickTopOrderButton() {
        driver.findElements(topOrderButton).get(0).click();
    }

    public void clickBottomOrderButton() {
        driver.findElements(bottomOrderButton).get(0).click();
    }

    public void clickQuestion(int index) {
        driver.findElements(faqQuestion).get(index).click();
    }

    public String getAnswerText(int index) {
        return driver.findElements(faqAnswer).get(index).getText();
    }
}