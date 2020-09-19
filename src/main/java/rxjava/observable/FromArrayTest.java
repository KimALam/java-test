package rxjava.observable;

import io.reactivex.Observable;

public class FromArrayTest {
    public static void main(String[] args) {
        Integer[] arr = {100, 200, 300};
        Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(System.out::println);

/* Only boxed type.
        int[] primitiveArr = {100, 200, 300};
        source = Observable.fromArray(primitiveArr);
        source.subscribe(System.out::println);
*/
    }
}
