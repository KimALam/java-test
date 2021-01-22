package test.reference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.ref.WeakReference;

class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
//        ReferenceTest test = new ReferenceTest();
//        WeakReference<Employer> wrefEmpr = new WeakReference<>(test.createEmployer());
        Employer empr = new Employer("alkim", 44);
        WeakReference<Employer> wrefEmpr = new WeakReference<>(empr);

        System.gc();
        Thread.sleep(1000);

        System.out.println("weak1 : " + wrefEmpr.get());

        empr = null;
        System.gc();
        Thread.sleep(1000);

        System.out.println("weak2 : " + wrefEmpr.get());
    }

    Employer createEmployer() {
        return new Employer("alkim", 44);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class Employer {
        String name;
        int age;
    }
}
