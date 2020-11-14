package rxjava.test.observable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableCreationTimeTest {
    public static void main(String[] args) {
        // 1. just()
//        Observable observable = Observable.just(getName())

        // 2. create()
        Observable observable = Observable.create(subscriber -> {
            String name = getName();
            subscriber.onNext(name);
            subscriber.onComplete();
        })

        // 3. defer()
//        Observable observable = Observable.defer(() -> {
//            String name = getName();
//            return Observable.just(name);
//        })

        // 4. fromCallable()
//        Observable observable = Observable.fromCallable(() -> getName())
        .map(s -> {
            log.info("map() : {}", s);
            return s;
        })
        .subscribeOn(Schedulers.io());

        log.info("end...");
        observable.subscribe(s -> log.info("subscribe {}", s));

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getName() {
        String name = "abcd";
        log.info("My name is {}", name);
        return name;
    }
}
