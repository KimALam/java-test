package test.time;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class TimeClassTest {
    public static void main(String[] args) {
        String timestamp = "2019-11-21T00:00:00.000+09:00";
        LocalDate end = LocalDate.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDate start = end.minusDays(1);

        System.out.println("local date: " + end);

        Period period = Period.between(start, end);

        System.out.println("perid year: " + period.getYears());
        System.out.println("perid month: " + period.getMonths());
        System.out.println("perid day: " + period.getDays());
    }
}
