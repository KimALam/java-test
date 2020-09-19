package rxjava.combine;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipWithTest {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20 ,30),
                (a, b) -> a +b)
                .zipWith(Observable.just(1, 2, 3), (ab, c) -> ab +c);
        source.subscribe(data -> log.info("onNext: {}", data));
    }
}
