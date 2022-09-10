package com.optional;

import java.util.Optional;

/**
 * Created by Dmytro Honchar
 * Date: 9/10/2022
 */
public class Main {
    public static void main(String[] args) {
        var bob = new Person("Bob", 26, Optional.of(new Car("Nisan", 1998)));
    }
}

class Person {
    private final String name;
    private final int age;
    private Optional<Car> car;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person(String name, int age, Optional<Car> car) {
        this(name, age);
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}

record Car(String model, int age) { }
