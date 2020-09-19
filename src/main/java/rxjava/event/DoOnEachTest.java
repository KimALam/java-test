package rxjava.event;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DoOnEachTest {
    public static void main(String[] args) {
        String[] data = {"ONE", "TWO", "THREE"};
        Observable<String> source = Observable.fromArray(data);

        // use Notification<T>
        source.doOnEach(noti -> {
            if (noti.isOnNext()) log.info("isOnNext() : {}", noti.getValue());
            if (noti.isOnComplete()) log.info("isOnComplete()");
            if (noti.isOnError()) log.info("isOnError() : {}", noti.getError().getMessage());
        }).subscribe(val -> log.info("onNext() : {}", val));


        String[] orgs = {"1", "3", "5"};
        Observable<String> source1 = Observable.fromArray(orgs);

        // use Observer
        source.doOnEach(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                // doOnEach()에서는 onSubscribe() 함수가 호출 되지 않습니다.
            }

            @Override
            public void onNext(String s) {
                log.info("Observer.onNext() : {}", s);
            }

            @Override
            public void onError(Throwable e) {
                log.info("Observer.onError() :{}", e.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("Observer.onComplete()");
            }
        }).subscribe(val -> log.info("onNext() : {}", val));
    }
}
