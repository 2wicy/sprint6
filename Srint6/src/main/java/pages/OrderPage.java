package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    private final By periodOption = By.xpath("//div[@class='Dropdown-option' and text()='сутки']");
    private final By colorCheckbox = By.id("black");
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By yesButton = By.xpath("//button[text()='Да']");
    private final By confirmationMessage = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUserInfo(String name, String surname, String addr, String metro, String tel) {
        driver.findElement(firstName).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(metroStation).sendKeys(metro, Keys.DOWN, Keys.ENTER);
        driver.findElement(phone).sendKeys(tel);
        driver.findElement(nextButton).click();
    }

    public void fillOrderDetails(String date, String comm) {
        driver.findElement(dateInput).sendKeys(date, Keys.ENTER);
        driver.findElement(rentalPeriod).click();
        driver.findElement(periodOption).click();
        driver.findElement(colorCheckbox).click();
        driver.findElement(comment).sendKeys(comm);
        driver.findElement(orderButton).click();
        driver.findElement(yesButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}