package rxjava.operator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlatMap2Test {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("aaaa", "bbbb", "cccc")
                .flatMap(item -> {
                    log.info("in flatMap: {}", item);
                    return Observable.fromCallable(() -> {
                        log.info("in flatMap.fromCallable: {}", item);
                        return item + "_callable";
                    }).repeat(2);
                })
                .map(t -> {
                    log.info("in map: {}", t);
                    return t + "_map";
                });

        source.subscribe(item -> log.info("onNext : {}", item));
    }
}
