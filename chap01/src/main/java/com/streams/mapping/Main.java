package com.streams.mapping;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

/**
 * Created by Dmytro Honchar
 * Date: 9/9/2022
 */
public class Main {
    public static void main(String[] args) {
        var set = Set.of("A", "B", "C");
        var map = Map.of("k1", "v1", "k2", "v2", "k3", "v3");
        var defaultMap = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
        var immutableList = List.of(1, 2, 3, 4, 5);
        var mutableList = Arrays.asList(1, 2, 3, 4, 5);
        var integerStream = Stream.of(1, 2, 3, 4, 5);
        var defaultList = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        Collections.addAll(defaultList, 3, 4, 5);
//      var list = Lists.newArrayList(1, 2, 3, 4, 5); // Guava

        var l = Aboba.squaring(immutableList);
        System.out.println(l);

        var ll = Aboba.iter(immutableList, i -> i * i);
        System.out.println(ll);

        var s = immutableList.stream()
                .map(i -> i * i)
                .toList();
        System.out.println(s);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .toList();
        pairs.forEach(i -> System.out.println(Arrays.toString(i)));

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        List<int[]> pairsWithFilter = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .toList();
        pairsWithFilter.forEach(i -> System.out.println(Arrays.toString(i)));

        var numbers = List.of(1, 2, 3, 4, 5, 6);
        int sum = numbers.stream().reduce(0, Integer::sum);
        int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum); // 21

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        Optional<Integer> min2 = numbers.stream().reduce((x, y) -> x < y ? x : y);
        System.out.println(min); // Optional[1]
        System.out.println(min.get()); // 1

        int count = numbers.stream()
                .map(d -> 1) // makes each element equal to 1
                .reduce(0, (a, b) -> a + b); // 1+1+1+1+1+1
        long count2 = numbers.stream().count();
        long count3 = numbers.size();
        System.out.println(count); // 6
    }
}

class Aboba {
    static List<Integer> squaring(List<Integer> list) {
        var squarList = new ArrayList<Integer>();
        for (var i : list) {
            squarList.add(i * i);
        }
        return squarList;
    }

    static List<Integer> iter(List<Integer> list, IntUnaryOperator f) {
        var a = new ArrayList<Integer>();
        for (var i : list) {
            a.add(f.applyAsInt(i));
        }
        return a;
    }
}
