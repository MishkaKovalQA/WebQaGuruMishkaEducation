package qa.github;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class GithubSelenideTests {

    @Test
    @DisplayName("Checking finding selenide repository at the search top")
    void shouldFindSelenideRepositoryAtTheTopTest() {
        open("https://github.com");
        $("[data-target='qbsearch-input.inputButton']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("[data-testid=results-list] a").first().click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }

    @Test
    @DisplayName("Checking top contributor in selenide repository")
    void solntsevShouldBeTheTopContributorTest() {
        open("https://github.com/selenide/selenide");
        $(".BorderGrid").$(byText("Contributors"))
                .ancestor(".BorderGrid-row")
                .$$("ul li").first().hover();
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }

    @Test
    @DisplayName("Checking soft assertions page contains JUnit5 info for selenide repository")
    void checkSoftAssertionsPageForSelenideRepositoryTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-box").$(".wiki-more-pages-link").$("button").click();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(".repository-content").shouldHave(text("Using JUnit5 extend test class"));
    }
}
