package rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GroupByTest {
    public static void main(String[] args) {
        String[] objs = {"6", "4", "2-T", "6-T", "4-T"};

        Observable<GroupedObservable<String, String>> source = Observable.fromArray(objs)
                .groupBy(GroupByTest::getShape);

        source.subscribe(obj -> obj.filter(val -> obj.getKey().equals("BALL"))
                .subscribe(val -> log.info("GROUP: {} \t value: {}", obj.getKey(), val)));
    }

    private static String getShape(String obj) {
        if (obj == null || obj.equals("")) return "NO-SHAPE";
        if (obj.endsWith("-H")) return "HEXAGON";
        if (obj.endsWith("-O")) return "OCTAGON";
        if (obj.endsWith("-R")) return "RECTANGLE";
        if (obj.endsWith("-T")) return "TRIANGLE";
        return "BALL";
    }
}
