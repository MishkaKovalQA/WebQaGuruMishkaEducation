package qa.demoqa.element;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;

@Slf4j
@RequiredArgsConstructor
public class CheckBox implements Clickable {

    private final String name;
    private final SelenideElement selector;

    @Step("Click check box {this.name}")
    public void click(String text) {
        log.info(("Click check box " + name));
        selector.$(byText(text)).click();
    }

    @Step("Click check box {this.name} from list")
    public void click(List<String> textList) {
        log.info(("Click check box " + name));
        textList.forEach(text
                -> selector.$(byText(text)).click());
    }
}
