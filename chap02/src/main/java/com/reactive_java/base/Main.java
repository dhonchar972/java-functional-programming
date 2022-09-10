package com.reactive_java.base;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
        //var sum = Arrays.stream(list).parallel().sum();
        var sum = IntStream.rangeClosed(0, 10_000).parallel().sum();
        System.out.println(sum); // 50005000

        // java.util.concurrent.RecursiveTask, can(must) create your own classes that extends RecursiveTask<>

        // static ExecutorService newFixedThreadPool(int nThreads) // generate ExecutorService,
        // that contains "worker threads" // task(Runnable or Callable) -> thread

        //Thread.setDaemon // after leaving main function, all threads-daemons dies

//        SomeClass classOne = new SomeClass();
//        Thread t1 = new Thread(() -> { classOne.x = function1(someVal); } );
//        Thread t2 = new Thread(() -> { classOne.y = function2(someVal); } );
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(classOne.x + classOne.y);

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Future<Integer> y = executorService.submit(() -> function1(someValue));
//        Future<Integer> z = executorService.submit(() -> function2(someValue));
//        System.out.println(y.get() + z.get());
//        executorService.shutdown();

        // ASYNC PARALLEL WORK: CompletableFuture, java.util.concurrent.Flow

//        Future<Integer> function1(int x);
//        Future<Integer> function2(int x);
//        Future<Integer> y = function1(someValue));
//        Future<Integer> z = function2(someValue));
//        System.out.println(y.get() + z.get());

//        void function1(int someValue, IntConsumer dealWithResult);

//        int someValue = 1337;
//        SomeClass classOne = new SomeClass();
//        function1(someValue, (int x) -> {
//            classOne.x = x;
//            System.out.println((classOne.x + classOne.y));
//        });
//        function2(someValue, (int y) -> {
//            classOne.y = y;
//            System.out.println((classOne.x + classOne.y));
//        });

//        void function(int someValue, Consumer<Integer> dealWithResult, Consumer<Throwable> dealWithException);

        // interface Subscriber<T> contains four methods treated as callbacks (object-wrapper):
            // void onComplete()
            // void onError(Throwable throwable)
            // void onNext(T item)

        // CompletableFuture
//        var executorService = Executors.newFixedThreadPool(10);
//        var x = 1337;
//        var a = new CompletableFuture<Integer>();
//        executorService.submit( () -> a.complete(function1(x)) );
//        var b = function2(x);
//        System.out.println(a.get() + b);
//        executorService.shutdown();

        // Futures composition
//        var executorService = Executors.newFixedThreadPool(10);
//        var x = 1337;
//        var a = new CompletableFuture<Integer>();
//        var b = new CompletableFuture<Integer>();
//        var c = a.thenCombine(b, Integer::sum);
//        executorService.submit(() -> a.complete(function1(x)));
//        executorService.submit(() -> b.complete(function2(x)));
//        System.out.println(c.get());
//        executorService.shutdown();

//        SimpleCell c3 = new SimpleCell("C3");
//        SimpleCell c2 = new SimpleCell("C2");
//        SimpleCell c1 = new SimpleCell("C1");
//        c1.subscribe(c3);
//        c1.onNext(10);
//        c2.onNext(20);
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");
        //c1.subscribe(c3::setLeft); // must be Consumer
        //c2.subscribe(c3::setRight); // must be Consumer
        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);
    }
}
