package qa.demoqa.element;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;

@Slf4j
@RequiredArgsConstructor
public class CheckBox {

    private final String name;
    private final SelenideElement selector;

    @Step("Click check box {this.name}")
    public void click(String hobby) {
        log.info(("Click check box " + name));
        selector.$(byText(hobby)).click();
    }

    @Step("Click check box {this.name} from list")
    public void click(List<String> hobbyList) {
        log.info(("Click check box " + name));
        hobbyList.forEach(hobby
                -> selector.$(byText(hobby)).click());
    }
}
