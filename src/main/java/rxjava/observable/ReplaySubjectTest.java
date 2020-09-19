package rxjava.observable;

import io.reactivex.subjects.ReplaySubject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplaySubjectTest {
    public static void main(String[] args) {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> log.info("Subscriber #1 => {}", data));

        subject.onNext("1");
        subject.onNext("2");

        subject.subscribe(data -> log.info("Subscriber #2 => {}", data));

        subject.onNext("11");
        subject.onNext("12");

        subject.onComplete();
    }
}
