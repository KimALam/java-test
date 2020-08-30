package test.loop;

import java.util.concurrent.atomic.AtomicInteger;

public class ForeEachTest {
    public static void main(String[] args) {
/*
        List<String> data = Arrays.asList("A", "B", "C", "D", "E");
        AtomicInteger i = new AtomicInteger(1);

        data.forEach(val -> {
            System.out.println("[" + Thread.currentThread().getName() + "] value = " + val);
            i.getAndIncrement();
        });

        int j = 1;
        for (String val : data) {
            System.out.println("[" + Thread.currentThread().getName() + "] value = " + val);
            j++;
        }
*/

        int iii = 0;
        increase(iii);
        increase(iii);
        System.out.println("iii: " + iii);

        AtomicInteger jjj = new AtomicInteger(0);
        increase(jjj);
        increase(jjj);
        System.out.println("jjj : " + jjj.toString());
    }

    private static void increase(int i) {
        i++;
    }

    private static void increase(AtomicInteger i) {
        i.incrementAndGet();
    }
}
