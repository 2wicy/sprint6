package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void checkAllFaqAnswersMatchExpectedText() {
        // Карта "вопрос - ожидаемый ответ"
        Map<String, String> expectedFaq = new HashMap<>();
        expectedFaq.put("Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        expectedFaq.put("Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
        expectedFaq.put("Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Аренда начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        expectedFaq.put("Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        expectedFaq.put("Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        expectedFaq.put("Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        expectedFaq.put("Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        expectedFaq.put("Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.");

        List<WebElement> questions = driver.findElements(By.className("accordion__button"));
        List<WebElement> answers = driver.findElements(By.className("accordion__panel"));

        for (int i = 0; i < questions.size(); i++) {
            WebElement question = questions.get(i);
            String questionText = question.getText();
            question.click();
            WebElement answer = answers.get(i);
            String answerText = answer.getText();

            Assertions.assertEquals(expectedFaq.get(questionText), answerText,
                    "Текст ответа для вопроса '" + questionText + "' не совпадает с ожидаемым");
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}