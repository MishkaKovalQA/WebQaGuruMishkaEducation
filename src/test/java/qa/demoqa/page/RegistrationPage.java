package qa.demoqa.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.demoqa.component.CalendarComponent;
import qa.demoqa.component.ModalResultsComponent;
import qa.demoqa.dto.RegistrationFormDataModel;
import qa.demoqa.element.Button;
import qa.demoqa.element.CheckBox;
import qa.demoqa.element.RadioButton;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private CalendarComponent calendarComponent = new CalendarComponent();
    private ModalResultsComponent modalResultsComponent = new ModalResultsComponent();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            phoneInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            fileInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateCityInput = $("#stateCity-wrapper"),
            stateInput = $("#state"),
            cityInput = $("#city");

    Button submitButton = new Button("Submit", $("#submit"));
    RadioButton genderInput = new RadioButton("Gender", $("#genterWrapper"));
    CheckBox hobbyInput = new CheckBox("Hobby", $("#hobbiesWrapper"));

    @Step("Open form")
    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $(".practice-form-wrapper").shouldHave(text("Practice Form"));

        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderInput.click(gender);

        return this;
    }

    public RegistrationPage setPhone(String phone) {
        phoneInput.setValue(phone);

        return this;
    }

    public RegistrationPage setDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();

        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        hobbyInput.click(hobby);

        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        fileInput.uploadFromClasspath(fileName);
        fileInput.shouldHave(value(fileName));

        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        stateInput.click();
        stateCityInput.$(byText(state)).click();
        cityInput.click();
        stateCityInput.$(byText(city)).click();

        return this;
    }

    public RegistrationPage clickSubmitButton() {
        submitButton.click();
        modalResultsComponent.checkAppeared();

        return this;
    }

    @Step("Verify results")
    public RegistrationPage checkFormResults(RegistrationFormDataModel data) {
        var rows = $$(".table tbody tr");
        rows.findBy(text("Student Name")).shouldHave(text(data.getFirstName() + " " + data.getLastName()));
        rows.findBy(text("Student Email")).shouldHave(text(data.getEmail()));
        rows.findBy(text("Gender")).shouldHave(text(data.getGender()));
        rows.findBy(text("Mobile")).shouldHave(text(data.getPhone()));
        rows.findBy(text("Date of Birth")).shouldHave(text(data.getDay() + " " + data.getMonth() + "," + data.getYear()));
        rows.findBy(text("Subjects")).shouldHave(text(data.getSubject()));
        rows.findBy(text("Hobbies")).shouldHave(text(data.getHobby()));
        rows.findBy(text("Picture")).shouldHave(text(data.getFileName()));
        rows.findBy(text("Address")).shouldHave(text(data.getAddress()));
        rows.findBy(text("State and City")).shouldHave(text(data.getState() + " " + data.getCity()));

        return this;
    }

    @Step("Fill form")
    public RegistrationPage fillRegistrationForm(RegistrationFormDataModel data) {
        setFirstName(data.getFirstName());
        setLastName(data.getLastName());
        setEmail(data.getEmail());
        setGender(data.getGender());
        setPhone(data.getPhone());
        setDate(data.getDay(), data.getMonth(), data.getYear());
        setSubject(data.getSubject());
        setHobby(data.getHobby());
        uploadPicture(data.getFileName());
        setAddress(data.getAddress());
        setStateAndCity(data.getState(), data.getCity());

        return this;
    }
}
