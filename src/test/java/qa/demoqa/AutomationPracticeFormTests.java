package qa.demoqa;

import org.junit.jupiter.api.Test;
import qa.BaseTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class AutomationPracticeFormTests extends BaseTest {

    @Test
    void testAutomationPracticeFormTest() {
        var firstName = "Mishka";
        var lastName = "Koval";
        var email = "mishka@gmail.com";
        var gender = "Male";
        var phone = "1234567890";
        var month = "May";
        var year = "1990";
        var hobby = "Sports";
        var fileName = "kitten.jpg";
        var address = "montenegro";
        var state = "Rajasthan";
        var city = "Jaipur";

        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $(".practice-form-wrapper").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month)").click();
        $(".subjects-auto-complete__value-container input").setValue("b");
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(fileName);
        $("#uploadPicture").shouldHave(value(fileName));
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $(".modal-dialog").should(appear);
        $(".modal-dialog").shouldHave(text("Thanks for submitting the form"));

        var rows = $$(".table tbody tr");
        rows.findBy(text("Student Name")).shouldHave(text(firstName + " " + lastName));
        rows.findBy(text("Student Email")).shouldHave(text(email));
        rows.findBy(text("Gender")).shouldHave(text(gender));
        rows.findBy(text("Mobile")).shouldHave(text(phone));
        rows.findBy(text("Date of Birth")).shouldHave(text("28" + " " + month + "," + year));
        rows.findBy(text("Subjects")).shouldHave(text("Biology"));
        rows.findBy(text("Hobbies")).shouldHave(text(hobby));
        rows.findBy(text("Picture")).shouldHave(text(fileName));
        rows.findBy(text("Address")).shouldHave(text(address));
        rows.findBy(text("State and City")).shouldHave(text(state + " " + city));
    }
}
