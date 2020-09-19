package rxjava.test.filtering;

import io.reactivex.Single;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class BreakTest {
    public static void main(String[] args) {
        List<String> result = Single.just("Start")
                .flatMap(key -> {
                    AtomicInteger i = new AtomicInteger(1);
                    // 상위 limit(=10) 만큼 랭킹을 뽑는다.
                    return Single.fromCallable(() -> new ArrayList<String>() {{ add("aaa"); add("bbb"); add("ccc"); }})
//                    return Single.fromCallable(() -> new ArrayList<String>())
                            .flattenAsObservable(l -> l)
                            .take(3)
                            .map(s -> new Dto(s, i.getAndIncrement()))
                            .toList();
                })
                .filter(l -> {
                    log.info("filtering");
                    return l.size() > 3;
                })
                .flatMapSingle(dtos -> {
                    final int total = dtos.stream().map(Dto::getRank).reduce(0, (a, b) -> a + b);

                    return Single.fromCallable(() -> new String("zzz"))
                            .map(String::valueOf)
                            .repeat(total)
                            .toList();
                })
                .onErrorReturn(e -> {
                    log.info("onErrorReturn: {}", e.getMessage());
                    return Collections.emptyList();
                })
                .blockingGet();

        log.info("result : {}", result);
    }

    @Getter
    private static class Dto {
        String name;
        int rank;

        public Dto(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
    }
}
