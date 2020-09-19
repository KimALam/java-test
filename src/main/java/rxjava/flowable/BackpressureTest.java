package rxjava.flowable;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackpressureTest {
    public static void main(String[] args) {
//        withoutBackpressure();
//        withBackpressure();
//        withBackpressureDrop();
        withBackpressureLatest();
    }

    public static void withoutBackpressure() {
        PublishSubject<Integer> subject = PublishSubject.create();

        subject.observeOn(Schedulers.computation())
                .subscribe(
                        data -> {
                            Thread.sleep(100);
                            log.info("onNext : {}", data);
                        },
                        err -> log.info("onError : {}", err.toString()));

        for (int i = 0; i < 50_000_000; i++) {
            subject.onNext(i);
        }
        subject.onComplete();
    }

    public static void withBackpressure() {
        Flowable.range(1, 50_000_000)
                .onBackpressureBuffer(128, () -> {}, BackpressureOverflowStrategy.DROP_OLDEST)
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> {
                            Thread.sleep(100);
                            log.info("onNext : {}", data);
                        },
                        err -> log.info("onError : {}", err.toString()));
    }

    // 버퍼가 차면 입력 버림. default buffer size = 128임으로 128 이후로 다 버린다.
    public static void withBackpressureDrop() {
        Flowable.range(1, 50_000_000)
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> {
                            Thread.sleep(100);
                            log.info("onNext : {}", data);
                        },
                        err -> log.info("onError : {}", err.toString()));

        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 버퍼가 차면 마지막 값을 제외하고 버림. default buffer size = 128
    public static void withBackpressureLatest() {
        Flowable.range(1, 50_000_000)
                .onBackpressureLatest()
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> {
                            Thread.sleep(100);
                            log.info("onNext : {}", data);
                        },
                        err -> log.info("onError : {}", err.toString()));

        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
