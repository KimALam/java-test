package rxjava.combine;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ReactiveSumExample {
    public static void main(String[] args) {
        new ReactiveSumExample().run();
    }

    public void run() {
        ConnectableObservable<String> source = userInput();

        Observable<Integer> a = source
                .filter(str -> str.startsWith("a:"))
                .map(str -> str.replace("a:", ""))
                .map(Integer::parseInt);

        Observable<Integer> b = source
                .filter(str -> str.startsWith("b:"))
                .map(str -> str.replace("b:", ""))
                .map(Integer::parseInt);

        Observable.combineLatest(a.startWith(0), b.startWith(0), (x, y) -> x + y)
                .subscribe(res -> log.info("onNext: {}", res));

        source.connect();
    }

    public ConnectableObservable<String> userInput() {
        return Observable.create((ObservableEmitter<String> emiter) -> {
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Input: ");
                String line = in.nextLine();
                emiter.onNext(line);

                if (line.indexOf("exit") >= 0) {
                    in.close();
                    break;
                }
            }
        }).publish();
    }
}
