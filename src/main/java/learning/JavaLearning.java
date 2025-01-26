package learning;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JavaLearning {

    public static void main(String[] args) {
        LocalDate date1, date2;
        RandomDate randomDate = new RandomDate();
        date1 = randomDate.generateDefault();
        date2 = randomDate.dayBounds(1, 3)
                .monthBounds(1, 3)
                .yearBounds(1900, 1910)
                .generate();
        String pattern = "dd/MM/yyyy";
        String formatDate1 = date1.format(DateTimeFormatter.ofPattern(pattern));
        String formatDate2 = date2.format(DateTimeFormatter.ofPattern(pattern));

        System.out.println(formatDate1);
        System.out.println(formatDate2);
    }
}
