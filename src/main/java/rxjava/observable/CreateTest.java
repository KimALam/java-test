package rxjava.observable;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateTest {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.create(emitter -> {
            log.info("In create...");
            emitter.onNext(100);
            emitter.onNext(200);
            emitter.onNext(300);
            emitter.onComplete();
        });

//        source.subscribe(next -> log.info("onNext: {}", next));
    }
}
