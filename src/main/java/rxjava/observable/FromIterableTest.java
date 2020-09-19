package rxjava.observable;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class FromIterableTest {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Jerry");
        names.add("William");
        names.add("Bob");

        Observable<String> source = Observable.fromIterable(names);
        source.subscribe(next -> log.info("onNext: {}", next));

        Set<String> cities = new HashSet<>();
        cities.add("Seoul");
        cities.add("London");
        cities.add("Paris");

        Observable<String> source2 = Observable.fromIterable(cities);
        source2.subscribe(next -> log.info("onNext2: {}", next));

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
        queue.add("ORD-1");
        queue.add("ORD-2");
        queue.add("ORD-3");

        Observable<String> source3 = Observable.fromIterable(queue);
        source3.subscribe(next -> log.info("onNext3: {}", next));
    }
}
