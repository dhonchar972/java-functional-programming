package com.streams.streams_tx;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmytro Honchar
 * Date: 9/9/2022
 */
public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        var tx = TxSorting.getSortedTransactions(transactions, 2011);
        tx.forEach(System.out::println);
        System.out.println(TxSorting.getUniqueCities(transactions));
        System.out.println(TxSorting.getUniqueTradersByCity(transactions, "Cambridge"));
        System.out.println(TxSorting.getUniqueTradersAsString(transactions));
        System.out.println(TxSorting.getAnyTraderFromCity(transactions, "Cambridge"));
        // entrySet()
        TxSorting.getNameAndValuesByCity(transactions, "Cambridge")
                .forEach(t ->
                        t.forEach((key, value) -> System.out.printf("Name: %s, Value: %d$\n", key, value)));
        System.out.println("Highest value: " + TxSorting.highestValue(transactions));
        System.out.println(TxSorting.betterSmallestTransaction(transactions));
    }
}
