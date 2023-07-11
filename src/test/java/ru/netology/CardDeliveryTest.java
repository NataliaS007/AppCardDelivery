package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void ShouldDeliverIfAllCorrect() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").setValue(ru.netology.DateSetup.setupDate());
        $("[name='name']").setValue("Семенова Наталья");
        $("[name='phone']").setValue("+79645000000");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification] .notification__title")
                .waitUntil(visible, 15000)
                .shouldBe(exactText("Успешно!"));
    }

    @Test
    void ShouldNotDeliverIfNameIsInvalid() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").setValue(ru.netology.DateSetup.setupDate());
        $("[name='name']").setValue("Semenova Natalia");
        $("[name='phone']").setValue("+79645000000");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldBe(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ShouldNotDeliverIfPhoneIsInvalid() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").setValue(ru.netology.DateSetup.setupDate());
        $("[name='name']").setValue("Семенова Наталья");
        $("[name='phone']").setValue("+796450000000");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[data-test-id='phone'] .input__sub").shouldBe(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
