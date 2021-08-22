package com.abumaster.javabase.juc;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 微基准测试工具的使用
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/5/3
 */
@BenchmarkMode(Mode.Throughput)
@Threads(8)
@Warmup(iterations = 2)
@Measurement(iterations = 10,time = 5,timeUnit = TimeUnit.SECONDS)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringBuilderBenchmark {

    @Benchmark
    public void testStringAdd() {
        String a="";
        for (int i = 0; i < 10; i++) {
            a+="a";
        }

    }


    @Benchmark
    public void testStringBuilder() {
        StringBuilder s=new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            s.append("a");
        }

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StringBuilderBenchmark.class.getSimpleName())
                .output("./Benchmark.log")
                .build();
        new Runner(options).run();
    }
}
