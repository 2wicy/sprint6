package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

public class FAQTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    void testFirstQuestionOpens() {
        mainPage.clickQuestion(0);
        Assertions.assertFalse(mainPage.getAnswerText(0).isEmpty());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}