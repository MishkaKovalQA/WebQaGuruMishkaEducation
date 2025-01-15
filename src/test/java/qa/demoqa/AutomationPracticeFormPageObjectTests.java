package qa.demoqa;

import org.junit.jupiter.api.Test;
import qa.BaseTest;
import qa.demoqa.dto.RegistrationFormDataModel;

class AutomationPracticeFormPageObjectTests extends BaseTest {

    @Test
    void testAutomationPracticeFormTest() {
        var dataInput = RegistrationFormDataModel.builder()
                .firstName("Mishka")
                .lastName("Koval")
                .email("mishka@gmail.com")
                .gender("Male")
                .phone("1234567890")
                .day("03")
                .month("May")
                .year("1990")
                .subject("Biology")
                .hobby("Sports")
                .fileName("kitten.jpg")
                .address("montenegro")
                .state("Rajasthan")
                .city("Jaipur").build();

        registrationPage.openPage()
                .fillRegistrationForm(dataInput)
                .clickSubmitButton()
                .checkFormResults(dataInput);
    }

    @Test
    void testAutomationPracticeFormWithJavaFakerTest() {
        var dataInput = RegistrationFormDataModel.builder().build();

        registrationPage.openPage()
                .fillRegistrationForm(dataInput)
                .clickSubmitButton()
                .checkFormResults(dataInput);
    }
}
