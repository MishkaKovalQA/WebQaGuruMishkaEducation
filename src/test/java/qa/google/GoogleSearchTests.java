package qa.google;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static domain.Tags.GOOGLE_TESTS;

@Tag(GOOGLE_TESTS)
class GoogleSearchTests extends BaseTest {

    @Test
    void successfulSearchWithSubmitButtonTest() {
        openGoogleSite();
        $("[name=q]").setValue("Selenide");
        $("[name=btnK]").click();
        checkSearchResult();
    }

    @Test
    void successfulSearchWithEnterTest() {
        openGoogleSite();
        $("[name=q]").setValue("Selenide").pressEnter();
        checkSearchResult();
    }

    private void checkSearchResult() {
        $("#search").shouldHave(text("https://ru.selenide.org"));
    }

    private void openGoogleSite() {
        open("https://www.google.com");
    }
}
