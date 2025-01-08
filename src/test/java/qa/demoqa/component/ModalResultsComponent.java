package qa.demoqa.component;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ModalResultsComponent {

    public void checkAppeared() {
        $(".modal-dialog").should(appear);
        $(".modal-dialog").shouldHave(text("Thanks for submitting the form"));
    }
}
