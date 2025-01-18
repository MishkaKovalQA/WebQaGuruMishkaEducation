package qa.github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.github.steps.AttachmentsSteps;
import qa.github.steps.WebSteps;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubAllureTests {

    private static final String REPOSITORY = "MishkaKovalQA/skills-copilot-codespaces-vscode";

    @Test
    @Feature("Issue in repo")
    @Story("Get Issue")
    @Owner("m.koval")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Checking issue in github repository with step methods and static labels")
    public void shouldHaveIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open github main page", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });
        step("Find repository by name " + REPOSITORY, () -> {
            $("[data-target='qbsearch-input.inputButton']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Enter repository " + REPOSITORY, () ->
                $(linkText(REPOSITORY)).click());
        step("Click issues tab", () ->
                $("#issues-tab").click());
        step("Check first issue", () ->
                $(withText("#1")).should(exist));
    }

    @Test
    public void shouldHaveIssueWebStepsTest() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Checking issue in github repository with step annotation and dynamic labels")
        );
        Allure.feature("Issue in repo");
        Allure.story("Get Issue");
        Allure.label("owner", "m.koval");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        WebSteps steps = new WebSteps();
        AttachmentsSteps attachments = new AttachmentsSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openMainPage();
        attachments.takeScreenshot();
        steps.findRepositoryByName(REPOSITORY);
        steps.enterRepository(REPOSITORY);
        steps.clickIssuesTab();
        steps.checkIssue(1);
    }
}
