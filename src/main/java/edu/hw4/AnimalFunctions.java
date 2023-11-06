package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalFunctions {
    private AnimalFunctions() {

    }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static List<Animal> task2(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static HashMap<Type, Long> task3(List<Animal> animals) {
        return (HashMap<Type, Long>) animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.counting()
            ));
    }

    public static Animal task4(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length())).get();
    }

    public static Sex task5(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.partitioningBy(animal -> Sex.F.equals(animal.sex())),
                map -> whoMore(map.get(false).size(), map.get(true).size())
            ));
    }

    private static Sex whoMore(int maleCount, int femaleCount) {
        return (maleCount > femaleCount) ? Sex.M : Sex.F;
    }

    public static HashMap<Type, Animal> task6(List<Animal> animals) {
        return (HashMap<Type, Animal>) animals.stream()
            .collect(Collectors
                .toMap(Animal::type, Function.identity(), BinaryOperator
                    .maxBy(Comparator.comparingInt(Animal::weight))));
    }

    public static Animal task7(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(Animal::age)).get();
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {
        return animals.stream()
            .filter(a -> a.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static int task9(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream()
            .filter(a -> (a.age() != a.paws()))
            .collect(Collectors.toList());
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.height() > 100 && animal.bites())
            .collect(Collectors.toList());
    }

    public static int task12(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .collect(Collectors.toList());
    }

    public static boolean task14(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> Type.DOG.equals(animal.type()) && animal.height() > k);
    }

    public static int task15(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .mapToInt(Animal::weight).sum();
    }

    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .collect(Collectors.toList());
    }

    public static boolean task17(List<Animal> animals) {
        double percentageOfDogs = animals.stream()
            .filter(animal -> Type.DOG.equals(animal.type()))
            .collect(Collectors.collectingAndThen(
                Collectors.partitioningBy(Animal::bites),
                map -> {
                    int dogBitesCount = map.get(true).size();
                    int allDogCount = map.get(false).size() + dogBitesCount;
                    if (allDogCount == 0) {
                        return (double) 0;
                    }
                    return (double) dogBitesCount / allDogCount;
                }
            ));
        double percentageOfSpider = animals.stream()
            .filter(animal -> Type.SPIDER.equals(animal.type()))
            .collect(Collectors.collectingAndThen(
                Collectors.partitioningBy(Animal::bites),
                map -> {
                    int spiderBitesCount = map.get(true).size();
                    int allSpiderCount = map.get(false).size() + spiderBitesCount;
                    if (allSpiderCount == 0) {
                        return (double) 0;
                    }
                    return (double) spiderBitesCount / allSpiderCount;
                }
            ));
        return percentageOfSpider > percentageOfDogs;
    }

    public static Animal task18(List<Animal> animals1, List<Animal> animals2) {
        return Stream.concat(animals1.stream(), animals2.stream())
            .filter(animal -> Type.FISH.equals(animal.type()))
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !ValidationError.checkValidate(animal).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                ValidationError::checkValidate
            ));
    }

    public static Map<String, String> task20(List<Animal> animals) {
        return AnimalFunctions.task19(animals).entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                animal -> animal.getValue().stream()
                    .map(ValidationError::getError)
                    .reduce((x, y) -> x + ", " + y).get()
            ));
    }
}
