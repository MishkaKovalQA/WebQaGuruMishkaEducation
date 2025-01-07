package qa.demoqa;

import org.junit.jupiter.api.Test;
import qa.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class AutomationPracticeFormTests extends BaseTest {

    @Test
    void testAutomationPracticeFormTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("Mishka");
        $("#lastName").setValue("Koval");
        $("#userEmail").setValue("mishka@gmail.com");
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--028").click();
        $(".subjects-auto-complete__value-container input").setValue("b");
        $(".subjects-auto-complete__option").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFromClasspath("kitten.jpg");
        $("#currentAddress").setValue("montenegro");
        $(byText("Select State")).click();
        $(byText("Rajasthan")).click();
        $(byText("Select City")).click();
        $(byText("Jaipur")).click();

        $("#submit").click();

        var rows = $$(".table.table-dark tbody tr");
        rows.findBy(text("Student Name")).shouldHave(text("Mishka Koval"));
        rows.findBy(text("Student Email")).shouldHave(text("mishka@gmail.com"));
        rows.findBy(text("Gender")).shouldHave(text("Male"));
        rows.findBy(text("Mobile")).shouldHave(text("1234567890"));
        rows.findBy(text("Date of Birth")).shouldHave(text("28 May,1990"));
        rows.findBy(text("Subjects")).shouldHave(text("Biology"));
        rows.findBy(text("Hobbies")).shouldHave(text("Sports"));
        rows.findBy(text("Picture")).shouldHave(text("kitten.jpg"));
        rows.findBy(text("Address")).shouldHave(text("montenegro"));
        rows.findBy(text("State and City")).shouldHave(text("Rajasthan Jaipur"));
    }
}
