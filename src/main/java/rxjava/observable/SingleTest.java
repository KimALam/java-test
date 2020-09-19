package rxjava.observable;

import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleTest {
    public static void main(String[] args) {
        Single<String> source = Single.just("Hello Single");
        source.subscribe(s -> log.info("onSuccess: {}", s));

        // 1) Observable to Single
        Observable<String> source1 = Observable.just("Hello Single");
        Single.fromObservable(source1).subscribe(s -> log.info("1) onSuccess: {}", s));

        // 2) single()
        Observable.just("Hello Single")
                .single("Default Item")
                .subscribe(s -> log.info("2) onSuccess: {}", s));

        // 3) first()
        String[] colors = {"Red", "Blue", "Gold"};
        Observable.fromArray(colors)
                .first("Default Value")
                .subscribe(s -> log.info("3) onSuccess: {}", s));

        // 4) empty Observable to Single
        Observable.empty()
                .single("Default Value")
                .subscribe(s -> log.info("4) onSuccess: {}", s));

        // 5) take()
        Observable.just(new Order("ORD-1"), new Order("ORD-2"))
                .take(1)
                .single(new Order("Default Order"))
                .subscribe(order -> log.info("5) onSuccess: {}", order));

        // error - multi value while Single
        Single<String> source2 = Observable.just("Hello Single", "Error")
                .single("Default Item");
        source2.subscribe(
                s -> log.info("Error| onSuccess: {}", s),
                err -> log.info("Error| onError: {}", err.getMessage()));
    }

    @Getter
    @Setter
    @ToString
    public static class Order {
        private String order;

        public Order(String order) {
            this.order = order;
        }
    }
}
