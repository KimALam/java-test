package test.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.of(1999, 10, 10, 1, 2, 22);
        System.out.println(now);

        LocalDateTime t1 = now.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(t1);

        LocalDateTime t2 = now.truncatedTo(ChronoUnit.SECONDS);
        System.out.println(t2);

        System.out.println("hour :" + now.getHour());
        System.out.println("minute :" + now.getMinute());

        System.out.println(now.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}
