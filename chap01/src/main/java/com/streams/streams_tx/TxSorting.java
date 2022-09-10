package com.streams.streams_tx;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Dmytro Honchar
 * Date: 9/9/2022
 */
public abstract class TxSorting {
    public static List<Transaction> getSortedTransactions(@NotNull List<Transaction> tx, int year) {
        return tx.stream()
                .filter(t -> t.getYear() == year)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .toList();
    }

    public static List<String> getUniqueCities(@NotNull List<Transaction> tx) {
        return tx.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .toList();
    }

    public static List<String> getUniqueTradersByCity(@NotNull List<Transaction> tx, String city) {
        return tx.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals(city))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .toList();
    }

    public static String getUniqueTradersAsString(@NotNull List<Transaction> tx) {
        return tx.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public static Transaction getAnyTraderFromCity(@NotNull List<Transaction> tx, String city) {
        return tx.stream()
                .filter(t -> t.getTrader().getCity().equals(city))
                .findAny()
                .orElse(null);
        // findAny(), findFirst(), anyMatch(lambda-filter)->boolean

    }

    public static List<Map<String, Integer>> getNameAndValuesByCity(@NotNull List<Transaction> tx, String city) {
        return tx.stream()
                .filter(t -> city.equals(t.getTrader().getCity()))
                .map(t -> Map.of(t.getTrader().getName(), t.getValue()))
                .toList();
    }

    public static int highestValue(@NotNull List<Transaction> tx) {
        return tx.stream()
                .mapToInt(Transaction::getValue)
                .reduce(Integer::max).orElse(0);
    }

    public static Transaction smallestTransaction(@NotNull List<Transaction> tx) {
        return tx.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2).orElse(null);
    }

    public static Transaction betterSmallestTransaction(@NotNull List<Transaction> tx) {
        return tx.stream()
                .min(Comparator.comparing(Transaction::getValue)).orElse(null);
    }
}
