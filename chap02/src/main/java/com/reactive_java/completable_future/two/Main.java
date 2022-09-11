package com.reactive_java.completable_future.two;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        var futureTask = new FutureTask<Integer>(new Callable<Integer>() {
//            @Override public Integer call() throws InterruptedException {
//                int d = 1;
//                for (int i = 1; i < 32; i++) {
//                    d *= i;
//                    Thread.sleep(300); }
//                return d; }});
//        new Thread(futureTask).start();
//        System.out.println("Thread is not blocking!!!111");
//        System.out.println(futureTask.get());
//        System.out.println("Thread is not blocking!!!222");

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("CompletableFuture run!!!");
        });
///////////////////////////////////////////////////////////////////////////////////////////////////
        var executor = Executors.newCachedThreadPool();
        var future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                int d = 1;
                for (int i = 1; i < 32; i++) {
                    d *= i;
                    Thread.sleep(300);
                }
                return d;
            }});
        Thread newThread = new Thread(() -> {
            System.out.println("Thread is not blocking!!!222");
        });
        System.out.println("Thread is not blocking!!!111");
        newThread.start();
        System.out.println(future.get());
        executor.shutdown();
//////////////////////////////////////////////////////////////////////////////////////////////////////////
        var completableFuture = CompletableFuture.supplyAsync(() -> {
            int d = 1;
            for (int i = 1; i < 32; i++) {
                d *= i;
            }
            return d;
        });
        System.out.println(completableFuture.get(1, TimeUnit.SECONDS));
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//        public List<String> findPrices(String product) {
//            List<CompletableFuture<String>> priceFutures = shops.stream()
//                    .map(shop -> CompletableFuture.supplyAsync(
//                            () -> shop.getName() + " price is " + shop.getPrice(product)))
//                    .toList();
//            return; priceFutures.stream()
//                    .map(CompletableFuture::join)
//                    .toList();
//        }
    }
}
