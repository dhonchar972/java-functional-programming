package com.delegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Dmytro Honchar
 * Date: 9/6/2022
 */
public class Main {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();

        apples.addAll(Arrays.asList(
                new Apple("Gala", Color.GREEN, 75),
                new Apple("Golden", Color.YELLOW, 156),
                new Apple("Empire", Color.RED, 172),
                new Apple("Fuji", Color.GREEN, 85)
        ));

        List<Apple> sortedList = Sorter.prettySortObject(apples, (a) ->
                //Color.GREEN.equals(a.color()) && a.weight() > 80);
                a.color() == Color.GREEN && a.weight() > 80);

        System.out.println(sortedList.toString());
    }
}

enum Color { RED, GREEN, BLUE, YELLOW }

record Apple(String name, Color color, double weight) { }

class Sorter {
    static <T> List<T> prettySortObject(List<T> objects, Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        for (T obj : objects) {
            if (predicate.test(obj)) {
                list.add(obj);
            }
        }
        return list;
    }
}
