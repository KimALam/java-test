package rxjava.test.benchmark;

import io.reactivex.Single;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MapBenchmark {
    @Benchmark
    @Fork(value = 1, warmups = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void bench_map_1_time(Blackhole blackhole) {
        blackhole.consume(new MapBenchmark().map_1_time());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void bench_map_2_times(Blackhole blackhole) {
        blackhole.consume(new MapBenchmark().map_2_times());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void bench_map_3_times(Blackhole blackhole) {
        blackhole.consume(new MapBenchmark().map_3_times());
    }

    public String map_1_time() {
        return Single.just("1")
                .map(i -> i)
                .blockingGet();
    }

    public String map_2_times() {
        return Single.just("1")
                .map(i -> i)
                .map(i -> i)
                .blockingGet();
    }

    public String map_3_times() {
        return Single.just("1")
                .map(i -> i)
                .map(i -> i)
                .map(i -> i)
                .blockingGet();
    }

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }
}
