package test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeClassesTest {
    public static void main(String[] args) {
//        LocalDateTime date = LocalDateTime.now();
//        System.out.println("now : " + date);
//        System.out.println("-1 : " + date.minusDays(1));
        int[] a = new int[]{1,2,3};
        int[] b = a.clone();

        LocalDate date = LocalDate.now();
//        System.out.println("now : " + localDate1);
//        System.out.println("start of day : " + localDate1.atStartOfDay());
//        System.out.println("midnight : " + localDate1.atTime(LocalTime.MIDNIGHT));
//        System.out.println("max : " + localDate1.atTime(LocalTime.MAX));
//        System.out.println("format : " + localDate1.atTime(0, 0, 0));
//        System.out.println("format : " + localDate1.atTime(23, 59, 59));

        System.out.println("ISO 8601 : " + date.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("ISO 8601 : " + date.atTime(23, 59, 59).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println("ISO 8601 : " + date.atStartOfDay().minusSeconds(1));
        System.out.println("First day of week : " + date.with(DayOfWeek.MONDAY).minusDays(1));
    }
}
