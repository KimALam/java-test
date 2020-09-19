package rxjava.condition;

import rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllTest {
    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4"};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);

        source.subscribe(i -> log.info("onNext : {}", i));
    }
}
