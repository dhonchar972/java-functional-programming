package com.parallel_streams;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
        var n = Stream.iterate(1L, i -> i + 1)
                .limit(5)
                .parallel()
                .reduce(0L, Long::sum);

//        stream.parallel() // make stream parallel, use ForkJoinPool
//                .filter(...)
//                .sequential() // make stream coherent(sequential) again!
//                .map(...)
//                .parallel()
//                .reduce();

        System.out.println(forkJoinSum(10_000));

//        public interface Spliterator<T> {
//            boolean tryAdvance(Consumer<? super T> action);
//            Spliterator<T> trySplit();
//            long estimateSize();
//            int characteristics();
//        }
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static class ForkJoinSumCalculator extends RecursiveTask<Long> {
        private final long[] numbers;
        private final int start;
        private final int end;
        public static final long THRESHOLD = 10_000;

        public ForkJoinSumCalculator(long[] numbers) {
            this(numbers, 0, numbers.length);
        }

        private ForkJoinSumCalculator(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                return computeSequentially();
            }
            var leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
            leftTask.fork();

            var rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            return  leftResult + rightResult;
        }
        private long computeSequentially() {
            long sum = 0;
            for (int i = 0; i < end; i++) {
                sum += numbers[i];
            }
            return  sum;
        }
    }
}
