package rxjava.combine;

import rxjava.common.Shape;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipTest {
    public static void main(String[] args) {
        String[] shapes = {"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-7", "4-T"};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix);

        source.subscribe(data -> log.info("Subscriber #1: {}", data));
    }
}
