package com.collectors;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
//        var transactionsByCurrencies = new HashMap<Currency, List<Transaction>>();
//
//        for (Transaction transaction : transactions) {
//            Currency currency = transaction.getCurrency();
//            var transactionsForCurrency = transactionsByCurrencies.get(currency);
//
//            if (transactionsForCurrency == null) {
//                transactionsForCurrency = new ArrayList<>();
//                transactionsByCurrencies.put(currency, transactionsForCurrency);
//            }
//            transactionsForCurrency.add(transaction);
//        }

//        Map<Currency, List<Transaction>> transactionsByCurrencies =
//                transactions.stream().collect(groupingBy(Transaction::getCurrency));

//        var totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

//        enum CaloricLevel { DIET, NORMAL, FAT }
//        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
//                groupingBy(dish -> {
//                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//                    else return CaloricLevel.FAT;
//                } ));

//        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream().filter(dish ->
//                        dish.getCalories() > 500).collect(groupingBy(Dish::getType));

//        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
//                        .collect(groupingBy(Dish::getType,
//                                filtering(dish -> dish.getCalories() > 500, toList())));

//        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
//                        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

//        Map<Dish.Type, Set<String>> dishNamesByType = menu.stream()
//                        .collect(groupingBy(Dish::getType,
//                                flatMapping(dish -> dishTags.get( dish.getName() ).stream(), toSet())));

//        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));

//        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
//                        .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

//        menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
//        menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType)));
//        menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
    }
}
