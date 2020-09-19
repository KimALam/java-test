package rxjava.condition;

import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwitchIfEmptyTest {
    public static void main(String[] args) {
        String result = Single.just("aaa")
                .filter(t -> t.equals("aaa"))
                .switchIfEmpty(Maybe.just("from switchIfEmpty"))
                .blockingGet();
//                .subscribe(val -> log.info("onNext : {}", val));

        log.info("result : {}", result);
    }
}
