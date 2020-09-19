package rxjava.observable;

import io.reactivex.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PublishSubjectTest {
    public static void main(String[] args) {
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(data -> log.info("Subscriber #1 => {}", data));

        subject.onNext("1");
        subject.onNext("2");

        subject.subscribe(data -> log.info("Subscriber #2 => {}", data));

        subject.onNext("10");
        subject.onNext("11");

        subject.onComplete();
    }
}
