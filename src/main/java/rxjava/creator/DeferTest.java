package rxjava.creator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

@Slf4j
public class DeferTest {
    private static Iterator<String> colors = Arrays.asList("1", "3", "5", "6").iterator();

    public static void main(String[] args) {
        Callable<Observable<String>> supplier = () -> getObservable();
        Observable<String> source = Observable.defer(supplier);

        source.subscribe(val -> log.info("Subscriber #1 : {}", val));
        source.subscribe(val -> log.info("Subscriber #1 : {}", val));
    }

    private static Observable<String> getObservable() {
        if (colors.hasNext()) {
            String color = colors.next();
            return Observable.just(color + "-B", color + "-R", color + "-P");
        }
        return Observable.empty();
    }
}
