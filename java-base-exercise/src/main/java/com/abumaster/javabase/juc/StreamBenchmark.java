package com.abumaster.javabase.juc;

import cn.hutool.core.lang.Console;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 并行流和普通函数的
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/5/3
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(2)
@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class StreamBenchmark {

    @Param({"10000", "100000", "1000000"})
    private int length;
    private int[] numbers;

    @Benchmark
    public int multiThreadSum() {
        return IntStream.of(numbers).parallel().sum();
    }
    @Benchmark
    public int singleSum() {
        return IntStream.of(numbers).sum();
    }

    @Setup
    public void prepare() {
        numbers=IntStream.rangeClosed(0,length).toArray();
    }

    @TearDown
    public void clean() {

    }


    public static void main(String[] args) throws RunnerException {
//        Options options = new OptionsBuilder()
//                .include(StreamBenchmark.class.getSimpleName())
//                .build();
//        new Runner(options).run();
        Console.log("hello \\{}","dsfafd");
    }
}
