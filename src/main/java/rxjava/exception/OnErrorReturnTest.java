package rxjava.exception;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnErrorReturnTest {
    public static void main(String[] args) {
        String[] grades = {"70", "88", "$100", "93", "83"};

        // 1. 발행하는 측에서 exception 처리후 전달
        Observable<Integer> source = Observable.fromArray(grades)
                .map(data -> Integer.parseInt(data))
                .onErrorReturn(e -> {
                    log.info("onErrorReturn: {}", e.getMessage());
                    if (e instanceof NumberFormatException) {
                        e.printStackTrace();
                    }
                    return -1;
                });

        source.subscribe(data -> {
            if (data < 0) {
                log.info("Wrong data found!!!");
                return;
            }

            log.info("Grade is {}", data);
        });

        // 2. 구독하는 측에서 exception 처리
/*
        Observable<Integer> source1 = Observable.fromArray(grades)
                .map(data -> Integer.parseInt(data));

        source1.subscribe(
                data -> log.info("Grade is {}", data),
                e -> {
                    if (e instanceof NumberFormatException) {
                        e.printStackTrace();
                    }
                    log.info("Wrong data found!!!");
                }
        );
*/

        // 3. onErroReturnItem() - exception 구분은 못함
/*
        Observable<Integer> source2 = Observable.fromArray(grades)
                .map(data -> Integer.parseInt(data))
                .onErrorReturnItem(-1);

        source2.subscribe(data -> {
            if (data < 0) {
                log.info("Wrong data found!!!");
                return;
            }
            log.info("Grade is {}", data);
        });
*/
    }
}
