package junit5;

import rxjava.common.GsonHelper;
import rxjava.common.OkHttpHelper;
import rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class JUnit5Basic {
    @DisplayName("JUnit 5 First Example")
    @Test
    void testFirst() {
        int expected = 3;
        int actual = 1 + 2;
        assertEquals(expected, actual);
    }

    @DisplayName("test getShape() Observable")
    @Test
    void testGetShapeObservable1() {
        String[] data = {"1", "2-R", "3-T"};
        Observable<String> source = Observable.fromArray(data)
                .map(Shape::getShape);

        String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};
        List<String> actual = new ArrayList<>();
        source.doOnNext(d -> log.info("doOnNext : " + d))
                .subscribe(actual::add);

        assertEquals(Arrays.asList(expected), actual);
    }

    @DisplayName("using TestObservable for getShape()")
    @Test
    void testGetShapeObservable2() {
        String[] data = {"1", "2-R", "3-T"};
        Observable<String> source = Observable.fromArray(data)
                .map(Shape::getShape);

        String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};
        source.test().assertResult(expected);
    }

    @DisplayName("assertFailure() example")
    @Test
    void assertFailureExample() {
        String[] data = {"100", "200", "%300"};
        Observable<Integer> source = Observable.fromArray(data)
                .map(Integer::parseInt);

        source.test().assertFailure(NumberFormatException.class, 100, 200);
    }

    @DisplayName("assertFailureAndMessage() example")
    @Test
    void assertFailureAndMessageExample() {
        String[] data = {"100", "200", "%300"};
        Observable<Integer> source = Observable.fromArray(data)
                .map(Integer::parseInt);

        source.test().assertFailureAndMessage(NumberFormatException.class, "For input string: \"%300\"", 100, 200);
    }

    @DisplayName("assertComplete() example")
    @Test
    void assertComplete() {
        Observable<String> source = Observable.create(
                (ObservableEmitter<String> emitter) -> {
                    emitter.onNext("Hello RxJava");
                    emitter.onComplete();
                }
        );

        source.test().assertComplete();
    }

    @DisplayName("test Observable.interval() wrong")
    @Test
    @Disabled
    void testIntervalWrongWay() {
        Observable<Integer> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(Long::intValue);

        // interval은 computation thread에서 동작함으로 main thread에서의 테스트는 잘못 되었다.
        source.doOnNext(val -> log.info("onNext : {}", val))
                .test().assertResult(0, 1, 2, 3, 4);
    }

    @DisplayName("test Observable.interval() right")
    @Test
    void testIntervalRightWay() {
        Observable<Integer> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(Long::intValue);

        source.doOnNext(val -> log.info("onNext : {}", val))
                .test()
                .awaitDone(1L, TimeUnit.SECONDS)
                .assertResult(0, 1, 2, 3, 4);
    }

    @DisplayName("http test")
    @Test
    void testHttp() {
        final String url = "https://api.github.com/users/yudong80";

        Observable<String> source = Observable.just(url)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get)
                .doOnNext(d -> log.info("doOnNext : {}", d))
                .map(json -> GsonHelper.parseValue(json, "name"))
                .observeOn(Schedulers.newThread());

        String expected = "Dong Hwan Yu";
        source.doOnNext(d -> log.info("doOnNext : {}", d))
                .test()
                .awaitDone(3, TimeUnit.SECONDS)
                .assertResult(expected);
    }
}
