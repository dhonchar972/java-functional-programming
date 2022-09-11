package com.reactive_java.rx_java;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
        Observable<String> strings = Observable.just( "first", "second" );
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
//        onePerSec.subscribe(i -> System.out.println(TempInfo.fetch( "New York" )));
//        onePerSec.blockingSubscribe(
//                i -> System.out.println(TempInfo.fetch( "New York" ))
//        );

//        public interface Observer<T> {
//            void onSubscribe(Disposable d);
//            void onNext(T t);
//            void onError(Throwable t);
//            void onComplete();
//        }
    }
}
