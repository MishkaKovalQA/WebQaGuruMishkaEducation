package qa.selenide;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;
import static constants.Tags.HEROKU_APP_TESTS;

@Tag(HEROKU_APP_TESTS)
public class HerokuAppTests {

    @Test
    public void dragAndDropByCommandTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDrop(to($("#column-b")));
        $$("#columns").first().shouldHave(text("B"));
    }

    @Test
    public void dragAndDropByActionsTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions().moveToElement($("#column-a"))
                .clickAndHold()
                .moveByOffset(200, 0)
                .release().perform();
        $$("#columns").first().shouldHave(text("B"));
    }
}
