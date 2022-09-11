package com.reactive_java.completable_future.one;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
        ComFut.play();
    }

    static class ComFut {
        private static final Random r = new Random();

        private static final Supplier<Integer> producer = () -> {
            var item = r.nextInt();
            System.out.println("Produced: " + item + " by " + Thread.currentThread().getName());
            return item;
        };

        private static final Consumer<Integer> consumer = i -> {
            System.out.println("Consumed: " + i + " by " + Thread.currentThread().getName());
        };

        public static void play() {
            while (true) {
                IntStream.range(0, 10).boxed().forEach(i ->
                        CompletableFuture.supplyAsync(producer)
                                .thenAcceptAsync(consumer));
                try {
                    Thread.sleep(500 + r.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
