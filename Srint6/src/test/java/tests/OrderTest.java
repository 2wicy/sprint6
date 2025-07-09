package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

public class OrderTest {
    WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.open();
    }

    @ParameterizedTest
    @CsvSource({
            "Катя, Иванова, Москва, Сокольники, +79998887766, 2025-07-11, Привезите до обеда",
            "Андрей, Сидоров, Санкт-Петербург, Технологический институт, +79001112233, 2025-07-12, Позвонить заранее"
    })
    void testTopOrderButton(String name, String surname, String address, String metro, String phone, String date, String comment) {
        mainPage.clickTopOrderButton();
        orderPage.fillUserInfo(name, surname, address, metro, phone);
        orderPage.fillOrderDetails(date, comment);
        Assertions.assertTrue(orderPage.getConfirmationMessage().contains("Заказ оформлен"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}