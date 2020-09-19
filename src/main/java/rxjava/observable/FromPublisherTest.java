package rxjava.observable;


import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Slf4j
public class FromPublisherTest {
    public static void main(String[] args) {
        Publisher<String> publisher = s -> {
            s.onNext("Hello Observable.fromPublisher()");
            s.onComplete();
        };

        Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(next -> log.info("onNext: {}", next));
    }
}
