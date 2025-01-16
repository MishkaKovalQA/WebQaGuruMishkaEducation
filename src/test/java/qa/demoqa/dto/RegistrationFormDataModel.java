package qa.demoqa.dto;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Locale;

@Data
@Builder
public class RegistrationFormDataModel {

    private static Faker faker() {
        return Faker.instance(Locale.ENGLISH);
    }

    @Builder.Default
    private String firstName = faker().name().firstName();

    @Builder.Default
    private String lastName = faker().name().lastName();

    @Builder.Default
    private String email = faker().internet().emailAddress();

    @Builder.Default
    private String gender = getRandomFromList(List.of("Male", "Female", "Other"));

    @Builder.Default
    private String phone = faker().phoneNumber().subscriberNumber(10);

    @Builder.Default
    private String day = setRandomDay();

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
    private String state = getRandomFromList(List.of("Uttar Pradesh", "NCR", "Haryana", "Rajasthan"));

    private String city;

    public String getCity() {
        if (city == null) {
            city = setRandomCity(this.state);
        }
        return city;
    }

    private static String getRandomFromList(List<String> options) {
        return options.get(faker().random().nextInt(0, options.size() - 1));
    }

    private static String setRandomDay() {
        int day = faker().number().numberBetween(1, 28);
        if (day < 10) {
            return "0" + day;
        } else {
            return day + "";
        }
    }

    private static String setRandomCity(String state) {
        String city;
        switch (state) {
            case "NCR" -> city = getRandomFromList(List.of("Delhi", "Gurgaon", "Noida"));
            case "Haryana" -> city = getRandomFromList(List.of("Karnal", "Panipat"));
            case "Rajasthan" -> city = getRandomFromList(List.of("Jaipur", "Jaiselmer"));
            case "Uttar Pradesh" -> city = getRandomFromList(List.of("Agra", "Lucknow", "Merrut"));
            default -> city = "Delhi";
        }
        return city;
    }

}
