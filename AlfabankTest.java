import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class AlfabankTest {
    @BeforeAll
    static void beforeAll() {
        baseUrl = "http://alfabank.ru";
        Configuration.pageLoadStrategy = "eager";
        String browserSize = "1920x1080";
    }

    @Test
    void testAlfa() {
        open(baseUrl);
        $(byText("Вклады")).click();
        $(byLinkText("Альфа-Счёт")).click();

        $(byName("fullName")).click();
        $(byName("fullName")).setValue("Сергеенко Алексей Сергеевич");
        $(byName("passportBirthDateField")).click();
        $(byName("passportBirthDateField")).setValue("01.01.1990");
        $(byName("phone")).setValue("9999860270");
        $(byName("phone")).setValue("9999860270");
        $(byName("email")).click();
        $(byName("email")).setValue("mail@mail.com");

        $("#alfa #form").shouldHave(appear);
        $("[value='Сергеенко']").shouldBe(visible);
        $("[value='Алексей']").shouldBe(visible);
        $("[value='Сергеевич']").shouldBe(visible);
        $(byText("Мужской")).click();

        $("[data-test-id=button]").click();
        $("[data-test-id=sms-confirmation-modal]").shouldHave(appear);
        $("[data-test-id=sms-confirmation-modal]").shouldHave(text("Введите код из смс"),
                text("+7 (999) 986-02-70"));

    }
}
