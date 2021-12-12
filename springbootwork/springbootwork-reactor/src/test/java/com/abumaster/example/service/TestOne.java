package com.abumaster.example.service;

import cn.hutool.core.thread.ThreadUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/12/12
 */
public class TestOne {

    public static void main(String[] args) {
        new TestOne().createFlux();
    }

    public void test1() {
        Mono.just("hello")
                .map(String::toUpperCase)
                .map(cn->"输出:"+cn)
                .subscribe(System.out::println);


    }

    /**
     * 创建flux的几种方式
     */
    public void createFlux() {
        // 1. 直接从对象中创建
        Flux<String> a = Flux.just("a", "b", "c");
        // 中间操作
        // 订阅操作
        // a.subscribe(System.out::println);

        StepVerifier.create(a)
                .expectNext("a")
                .expectNext("b")
                .expectNext("c")
                .verifyComplete();

        String[] fruits = new String[] {
                "Apple", "Orange", "Grape", "Banana", "Strawberry" };

        Flux<String> fruitFlux = Flux.fromArray(fruits);
        StepVerifier.create(fruitFlux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();

    // 从stream中创建
    Stream<String> fruitStream =
            Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");

    Flux<String> fruitFlux2 = Flux.fromStream(fruitStream);



    Flux.from(fruitFlux2).subscribe(System.out::println);


        Flux<Long> intervalFlux =
                Flux.interval(Duration.ofSeconds(1))
                        .take(5);

        intervalFlux.subscribe(System.out::println);
        ThreadUtil.sleep(10000);
    }
}
