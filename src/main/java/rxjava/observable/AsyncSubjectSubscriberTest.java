package rxjava.observable;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsyncSubjectSubscriberTest {
    public static void main(String[] args) {
        Float[] temperature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> log.info("Subscriber #1: {}", data));

        source.subscribe(subject);
    }
}
