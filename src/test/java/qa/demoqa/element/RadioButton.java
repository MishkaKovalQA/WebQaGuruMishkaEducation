package qa.demoqa.element;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selectors.byText;

@Slf4j
@RequiredArgsConstructor
public class RadioButton implements Clickable {

    private final String name;
    private final SelenideElement selector;

    @Step("Click radio button {this.name}")
    public void click(String text) {
        log.info(("Click radio button " + name));
        selector.$(byText(text)).click();
    }
}
