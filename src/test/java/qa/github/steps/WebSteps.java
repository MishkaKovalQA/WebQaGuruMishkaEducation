package qa.github.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Open github main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Find repository by name = {name}")
    public void findRepositoryByName(String name) {
        $("[data-target='qbsearch-input.inputButton']").click();
        $("#query-builder-test").setValue(name).pressEnter();
    }

    @Step("Enter repository {repository}")
    public void enterRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Click issues tab")
    public void clickIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check issue with number {number}")
    public void checkIssue(int number) {
        $(withText("#" + number)).should(exist);
    }
}
