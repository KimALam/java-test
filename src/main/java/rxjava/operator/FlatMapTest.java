package rxjava.operator;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class FlatMapTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        log.info("Gugudan Input : ");
        int dan = Integer.parseInt(in.nextLine());

        Observable<String> source = Observable.just(dan)
//        Observable<String> source = Observable.just(9)
                .flatMap(
                        gugu -> {
                            log.info("flatMap mapper: gugu = {}", gugu);
                            return Observable.range(1, 9);
                        },
                        (gugu, i) -> {
                            log.info("flatMap resultSelector: gugu = {}, i = {}", gugu, i);
                            return gugu + " * " + i + " = " + (gugu * i);
                        }
                );

        source.subscribe(data -> log.info("onNext: data = {}", data));
    }
}
