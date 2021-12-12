package com.abumaster.example.service;

import cn.hutool.core.thread.ThreadUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

/**
 * 合并数据流
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/12/12
 */
public class TestTwo {

    public static void main(String[] args) {
        new TestTwo().flatMapFluxes();
    }
    /** 合并两组数据*/
    public void mergeFluxes() {

        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa")
                .delayElements(Duration.ofMillis(500));

        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);

        mergedFlux.subscribe(System.out::println);

        // 休眠一会，等消费完
        ThreadUtil.sleep(10000);


    }
    /** 压缩两组数据，产生一个数组*/
    public void zipFluxes() {
        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples");

        Flux<Tuple2<String, String>> zippedFlux =
                Flux.zip(characterFlux, foodFlux);
        zippedFlux.subscribe(item->{
            System.out.println(item.getT1()+", "+item.getT2());
        });
        ThreadUtil.sleep(1000);
    }

    /** skip应用*/
    public void skipFluxes() {
        String[] arr = {"a", "b", "c", "d", "e", "f"};
        // 跳过前面3个元素的流
        Flux<String> skip1 = Flux.fromArray(arr).skip(3);
        StepVerifier.create(skip1)
                .expectNext("d")
                .expectNext("e")
                .expectNext("f")
                .verifyComplete();
        // 1s发送一个数据，从第4s开始取数据
        Flux<String> skip2 = Flux.fromArray(arr).delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofMillis(4000));
        StepVerifier.create(skip2)
                .expectNext("d")
                .expectNext("e")
                .expectNext("f")
                .verifyComplete();
    }

    /** flatMap的使用*/
public void flatMapFluxes() {
    Flux<String> flux1 = Flux.range(1,20)
            .flatMap(item -> Mono.just(item).map(x -> "string: " + x).subscribeOn(Schedulers.parallel()));

    flux1.subscribe(System.out::println);

    ThreadUtil.sleep(1000);
}
}
