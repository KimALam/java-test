package rxjava.event;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnErrorResumeNextTest {
    public static void main(String[] args) {
        String[] salesData = {"100", "200", "A300"};
        Observable<Integer> onParseError = Observable.defer(() -> {
            log.info("send email to administrator");
            return Observable.just(-1);
        }).subscribeOn(Schedulers.io());

        Observable<Integer> source = Observable.fromArray(salesData)
                .map(Integer::parseInt)
                .onErrorResumeNext(onParseError);

        source.subscribe(data -> {
            if (data < 0) {
                log.info("Wrong Data found!!!");
                return;
            }

            log.info("Sales data : {}", data);
        });
    }
}
