package learning;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDate {

    private int dayBounds, monthBounds, yearBounds;

    public LocalDate generateDefault() {
        var dayBounds = getRandomInt(1, 20);
        var monthBounds = getRandomInt(1, 12);
        var yearBounds = getRandomInt(1900, 2025);
        return LocalDate.of(yearBounds, monthBounds, dayBounds);
    }

    private static int getRandomInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }

    public LocalDate generate() {
        return LocalDate.of(yearBounds, monthBounds, dayBounds);
    }

    public RandomDate dayBounds(int from, int to) {
        dayBounds = getRandomInt(from, to);
        return this;
    }

    public RandomDate monthBounds(int from, int to) {
        monthBounds = getRandomInt(from, to);
        return this;
    }

    public RandomDate yearBounds(int from, int to) {
        yearBounds = getRandomInt(from, to);
        return this;
    }

}
