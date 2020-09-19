package rxjava.combine;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Slf4j
public class ZipTest3 {
    public static void main(String[] args) {
        String[] data = {"100", "300"};

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    if (val <= 200) return 910;
                    if (val <= 400) return 1600;
                    return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    double series1 = min(200, val) * 93.3;
                    double series2 = min(200, max(val - 200, 0)) * 187.9;
                    double series3 = min(0, max(val - 400, 0)) * 280.65;
                    return (int)(series1 + series2 + series3);
                });

        Observable<Pair<String, Integer>> source = Observable.zip(
                basePrice,
                usagePrice,
                Observable.fromArray(data),
                (v1, v2, i) -> Pair.of(i, v1 + v2));

        source.map(val -> Pair.of(val.left, new DecimalFormat("#,###").format(val.right)))
                .subscribe(val -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Usage: " + val.left + " kWh => ");
                    sb.append("Price: " + val.right + "원");
                    log.info(sb.toString());
                });
    }

    private static class Pair<K, V> {
        private K left;
        private V right;

        private Pair(K left, V right) {
            this.left = left;
            this.right = right;
        }

        public static <T, U> Pair<T, U> of(T left, U right) {
            return new Pair(left, right);
        }
    }
}
