package rxjava.observable;

import io.reactivex.subjects.AsyncSubject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsyncSubjectTest {
    public static void main(String[] args) {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.subscribe(data -> log.info("Subscriber #1 => {}", data));
        log.info("Subscribed #1...");

        subject.onNext("1");
        subject.onNext("2");

        subject.subscribe(data -> log.info("Subscriber #2 => {}", data));
        log.info("Subscribed #2...");

        subject.onNext("3");
        log.info("Before complete...");
        subject.onComplete();
        log.info("Completed...");

        subject.onNext("10");
        subject.subscribe(data -> log.info("Subscriber #3 => {}", data));
        subject.subscribe(data -> log.info("Subscriber #4 => {}", data));
    }
}
