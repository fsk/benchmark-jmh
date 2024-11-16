package org.fsk;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@State(value = Scope.Thread)
@BenchmarkMode(value = Mode.AverageTime)
@OutputTimeUnit(value = TimeUnit.MILLISECONDS)
public class Main3 {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(Main3.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(3)
                .resultFormat(ResultFormatType.JSON)
                .result("result2.json")
                .build();

        new Runner(opt).run();

    }

    @Benchmark
    public static List<Integer> pythagorasTriples1() {

        List<Integer> triples = new ArrayList<>();

        int total = 1000;

        for (int a = 1; a <= total; a++) {
            for (int b = 1; b <= total; b++) {
                for (int c = 1; c <= total; c++) {
                    if (
                            ((a < b) && (a < c) && (b < c) &&
                                    ((a*a) + (b*b) == (c*c))) &&
                                    (a + b + c) == total
                    ) {
                        triples.add(a);
                        triples.add(b);
                        triples.add(c);
                    }
                }
            }
        }
        return triples;
    }

    @Benchmark
    public static List<Integer> pythagorasTriples2() {

        List<Integer> triples = new ArrayList<>();

        int total = 1000;

        for (int a = 1; a < total / 3; a++) {
            for (int b = a + 1; b < total / 2; b++) {
                int c = total - a - b;
                if ((a * a) + (b * b) == (c * c)) {
                    triples.add(a);
                    triples.add(b);
                    triples.add(c);
                }
            }
        }
        return triples;
    }
}
