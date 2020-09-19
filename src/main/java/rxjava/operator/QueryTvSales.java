package rxjava.operator;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryTvSales {
    public static void main(String[] args) {
        List<Pair<String, Integer>> sales = new ArrayList<>();

        sales.add(Pair.of("TV", 2500));
        sales.add(Pair.of("Camera", 300));
        sales.add(Pair.of("TV", 1600));
        sales.add(Pair.of("Phone", 800));

        Maybe<Integer> tvSales = Observable.fromIterable(sales)
                .filter(sale -> "TV".equals(sale.getLeft()))
                .map(sale -> sale.getRight())
                .reduce((sale1, sale2) -> sale1 + sale2);
        tvSales.subscribe(data -> log.info("TV Sales: ${}", data));
    }

    @Getter
    private static class Pair<E, V> {
        private E left;
        private V right;

        private Pair(E left, V right) {
            this.left = left;
            this.right = right;
        }

        public static Pair of(String left, int right) {
            return new Pair(left, right);
        }
    }
}
