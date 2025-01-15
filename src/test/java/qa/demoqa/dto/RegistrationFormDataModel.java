package qa.demoqa.dto;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Builder
public class RegistrationFormDataModel {

    @Builder.Default
    private String firstName = faker().name().firstName();

    @Builder.Default
    private String lastName = faker().name().lastName();

    @Builder.Default
    private String email = faker().bothify("????##@gmail.com");

    @Builder.Default
    private String gender = getRandomFromList(List.of("Male", "Female", "Other"));

    @Builder.Default
    private String phone = faker().phoneNumber().subscriberNumber(10);

    @Builder.Default
    private String day = String.valueOf(faker().number().numberBetween(10, 28));

    @Builder.Default
    private String month = getRandomFromList(List.of(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    ));

    @Builder.Default
    private String year = String.valueOf(faker().number().numberBetween(1950, 2023));

    @Builder.Default
    private String subject = getRandomFromList(List.of("Maths", "Computer Science", "History", "English"));

    @Builder.Default
    private String hobby = getRandomFromList(List.of("Sports", "Music", "Reading"));

    @Builder.Default
    private String fileName = "kitten.jpg";

    @Builder.Default
    private String address = faker().address().fullAddress();

    @Builder.Default
    private String state = "NCR";

    @Builder.Default
    private String city = getRandomFromList(List.of("Delhi", "Gurgaon", "Noida"));

    private static String getRandomFromList(List<String> options) {
        return options.get(ThreadLocalRandom.current().nextInt(options.size()));
    }

    private static Faker faker() {
        return Faker.instance(Locale.ENGLISH);
    }
}
