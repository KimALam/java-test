package rxjava.test.filtering;

import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterCallTrace {
    public static void main(String[] args) {
        String result = Single.just("aaaa")
                .filter(obj -> {
                    log.info("filter");
                    return obj.endsWith("zzz");
                })
                .map(str -> {
                    log.info("map");
                    return str + "_passed";
                })
//                .flatMapSingle(str -> {
//                    log.info("map");
//                    return Single.just(str + "_passed");
//                })
//                .onErrorReturn(e -> {
//                    log.info("onErrorReturn: {}", e.getMessage());
//                    return "empty";
//                })
                .blockingGet();

        log.info("result : {}", result);
    }
}
