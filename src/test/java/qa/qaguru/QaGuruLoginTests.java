package qa.qaguru;

import com.codeborne.selenide.Configuration;
import domain.Browser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import qa.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class QaGuruLoginTests extends BaseTest {

    public static final String LOGIN_PAGE = "https://school.qa.guru/cms/system/login";
    public static final String EMAIL = "delienfx@gmail.com";
    public static final String VALID_PASSWORD = "34+DCe3AD";

    @Execution(ExecutionMode.SAME_THREAD)
    @ParameterizedTest
    @EnumSource(value = Browser.class)
    void successfulLoginWithEnterTest(Browser browser) {
        Configuration.browser = browser.getName();

        open(LOGIN_PAGE);
        checkLoginForm();

        fillLoginFormByPassword(VALID_PASSWORD);
        $("[name=password]").pressEnter();
        checkLoginedForm();

        logoutWithButton();
        checkLoginForm();
    }

    @Test
    void successfulLoginWithSubmitButtonTest() {
        open(LOGIN_PAGE);

        fillLoginFormByPassword(VALID_PASSWORD);
        loginWithButton();
        checkLoginedForm();
    }

    @Test
    void unsuccessfulLoginTest() {
        open(LOGIN_PAGE);
        fillLoginFormByPassword("WRONG");
        loginWithButton();

        $(".btn-success").shouldHave(text("Неверный пароль"));
        $(".logined-form").shouldNotHave(text("Здравствуйте"));
    }

    private void loginWithButton() {
        $(".btn-success").click();
    }

    private void logoutWithButton() {
        $(".btn-logout").click();
    }

    private void fillLoginFormByPassword(String password) {
        $("[name=email]").setValue(EMAIL);
        $("[name=password]").setValue(password);
    }

    private void checkLoginForm() {
        $(".login-form").shouldHave(text("Войти"));
    }

    private static void checkLoginedForm() {
        $(".logined-form").shouldHave(text("Здравствуйте"));
    }
}
