package rxjava.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleSchedulerTest {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> numbers = Observable.range(100, 5);
        Observable<String> chars = Observable.range(0, 5)
                .map(SingleSchedulerTest::numberToAlphabet);

        numbers.subscribeOn(Schedulers.single())
                .subscribe(i -> log.info("Subscriber #1 : {}", i));

        chars.subscribeOn(Schedulers.single())
                .subscribe(c -> log.info("Subscriber #2 : {}", c));

        Thread.sleep(500);
    }

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String numberToAlphabet(long x) {
        return Character.toString(ALPHABET.charAt((int) (x % ALPHABET.length())));
    }
}
