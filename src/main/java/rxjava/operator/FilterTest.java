package rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterTest {
    public static void main(String[] args) {
        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};

        Observable<String> src = Observable.fromArray(objs)
                .filter(obj -> obj.endsWith("CIRCLE"));
        src.subscribe(data -> log.info("onNext: {}", data));

        Integer[] numbers = {100, 200, 300, 400, 500};
        Single<Integer> single;
        Observable<Integer> source;

        // 1) first()
        single = Observable.fromArray(numbers).first(-1);
        single.subscribe(data -> log.info("first() : data = {}", data));

        // 2) last()
        single = Observable.fromArray(numbers).last(999);
        single.subscribe(data -> log.info("last() : data = {}", data));

        // 3) take()
        source = Observable.fromArray(numbers).take(3);
        source.subscribe(data -> log.info("take(3) : data = {}", data));

        // 4) takeLast()
        source = Observable.fromArray(numbers).takeLast(3);
        source.subscribe(data -> log.info("takeLast(3) : data = {}", data));

        // 5) skip()
        source = Observable.fromArray(numbers).skip(2);
        source.subscribe(data -> log.info("skip(2) : data = {}", data));

        // 6) skipLast()
        source = Observable.fromArray(numbers).skipLast(2);
        source.subscribe(data -> log.info("skipLast(2) : data = {}", data));
    }
}
