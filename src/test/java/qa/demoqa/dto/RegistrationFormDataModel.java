package qa.demoqa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationFormDataModel {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phone;
    private String day;
    private String month;
    private String year;
    private String subject;
    private String hobby;
    private String fileName;
    private String address;
    private String state;
    private String city;
}
