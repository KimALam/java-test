package rxjava.observable;

import io.reactivex.subjects.BehaviorSubject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BehaviorSubjectTest {
    public static void main(String[] args) {
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("100");

        subject.subscribe(data -> log.info("Subscriber #1 => {}", data));

        subject.onNext("1");
        subject.onNext("2");

        subject.subscribe(data -> log.info("Subscriber #2 => {}", data));

        subject.onNext("10");
        subject.onComplete();
    }
}
