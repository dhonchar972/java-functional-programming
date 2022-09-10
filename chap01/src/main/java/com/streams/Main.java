package com.streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Dmytro Honchar
 * Date: 9/7/2022
 */
public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeHighCaloricDishNames = menu.parallelStream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .toList(); // Stream Terminal Operation
        //allMatch(), anyMatch(), noneMatch(), collect(), count(), forEach(), min(), max(), reduce()

        System.out.println(threeHighCaloricDishNames); // [pork, beef, chicken]

        List<Dish> slicedMenu = menu.stream()
                .takeWhile(dish -> dish.getCalories() > 320)
                .toList();

        System.out.println(slicedMenu); // [pork, beef, chicken, french fries, rice]

        List<Dish> dropMenu = menu.stream()
                .dropWhile(dish -> dish.getCalories() > 320)
                .toList();

        System.out.println(dropMenu); // [season fruit, pizza, prawns, salmon]

        List<Integer> dishNamesLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .toList();

        System.out.println(dishNamesLengths);

        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> uniqueCharacters = Arrays.stream(arrayOfWords)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // merging two streams
                .distinct()
                .toList();
        System.out.println(uniqueCharacters);

        var pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples.limit(5).forEach(t ->
                System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        var stream = Stream.of("Modern ", "Java ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        var homeValue = System.getProperty("home");
        //var homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        var homeValueStream = Stream.ofNullable(System.getProperty("home")).toList();
        var values = Stream.of("config", "home", "user").flatMap(key ->
                Stream.ofNullable(System.getProperty(key))).toList();
        homeValueStream.forEach(System.out::println);
        values.forEach(System.out::println);

        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // Generalizations of Fibonacci numbers
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        // Generalizations of Fibonacci numbers
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println); // 2, 3, 5, 8, 13, 21, 34...

        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);
        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

        // Generator
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        IntStream ones = IntStream.generate(() -> 1);

        IntStream twos = IntStream.generate(new IntSupplier() {
            public int getAsInt() {
                return 2;
            }
        });

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}

class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type {MEAT, FISH, OTHER}
}
