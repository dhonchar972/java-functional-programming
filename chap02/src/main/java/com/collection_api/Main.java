package com.collection_api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
        var friendsList = Arrays.asList("Raphael", "Olivia", "Thibaut");
        friendsList.set(0, "Richard");
        // friendsList.add("Thibaut"); // generate UnsupportedOperationException
        Set<String> friendsSet = Stream.of("Raphael", "Olivia", "Thibaut")
                .collect(Collectors.toSet()); // or toMap()


        var set = Set.of("A", "B", "C");
        var map = Map.of("k1", "v1", "k2", "v2", "k3", "v3");
        var list = List.of(1, 2, 3, 4, 5);
        // list.add(7); // UnsupportedOperationException
        var stream = Stream.of(1, 2, 3, 4, 5);

        var ageOfFriends = Map.ofEntries(Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26));
        System.out.println(ageOfFriends);

        // removeIf, replaceAll
        //Entry.comparingByValue, Entry.comparingByKey
        Map<String, String> favouriteMovies = Map.ofEntries(Map.entry("Raphael", "One"),
                Map.entry("Cristina", "Two"),
                Map.entry("Olivia", "Three"));
        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        // computeIfAbsent, computeIfPresent, compute
        // replaceAll, replace
        // forEach, reduce, search
        // forEachKey, reduceKeys, searchKeys
        // forEachValue, reduceValues, searchValues
        // Map.Entry (forEachEntry, reduceEntries, searchEntries
        // mappingCount
    }
}
