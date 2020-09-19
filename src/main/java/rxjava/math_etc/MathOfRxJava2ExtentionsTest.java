package rxjava.math_etc;

import hu.akarnokd.rxjava3.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MathOfRxJava2ExtentionsTest {
    public static void main(String[] args) {
        Integer[] data = {1, 2, 3, 4};

        // 1. count()
        Single<Long> source = Observable.fromArray(data)
                .count();
        source.subscribe(i -> log.info("count() : {}", i));

        // 2. max() & min()
        Flowable.fromArray(data)
                .to(MathFlowable::max)
                .subscribe(i -> log.info("max() : {}", i));
        Flowable.fromArray(data)
                .to(MathFlowable::min)
                .subscribe(i -> log.info("min() : {}", i));

        // 3. sum() & average()
        Flowable<Integer> flowable = Flowable.fromArray(data)
                .to(MathFlowable::sumInt);
        flowable.subscribe(sum -> log.info("sum : {}", sum));

        Flowable<Double> flowable1 = Observable.fromArray(data)
                .toFlowable(BackpressureStrategy.BUFFER)
                .to(MathFlowable::averageDouble);
        flowable1.subscribe(avg -> log.info("average : {}", avg));
    }
}
