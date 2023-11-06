package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimalsTest {
    public List<Animal> animals = new ArrayList<>(List.of(
        new Animal("Fedor", Type.DOG, Sex.F, 4, 20, 30, true),
        new Animal("Fluffy", Type.FISH, Sex.F, 1, 5, 1, false),
        new Animal("Bella Blue Whale", Type.CAT, Sex.F, 2, 10, 15, true),
        new Animal("Charlie", Type.SPIDER, Sex.M, 3, 6, 2, false),
        new Animal("Luna", Type.BIRD, Sex.F, 5, 16, 3, false),
        new Animal("Oliver Owl", Type.CAT, Sex.M, 9, 21, 25, true),
        new Animal("Daisy", Type.FISH, Sex.F, 5, 7, 2, false),
        new Animal("Rocky", Type.DOG, Sex.M, 8, 22, 35, true),
        new Animal("Lucy", Type.BIRD, Sex.F, 3, 10, 12, false),
        new Animal("Sophie", Type.CAT, Sex.F, 6, 17, 23, false),
        new Animal("Lola", Type.SPIDER, Sex.F, 23, 32, 23, true),
        new Animal("Rocky Mountain Lion", Type.FISH, Sex.M, 12, 8, 5, true)
    ));

    public List<Animal> animals2 = new ArrayList<>(List.of(
        new Animal("Louis", Type.DOG, Sex.F, 4, 20, 30, true),
        new Animal("Coco", Type.FISH, Sex.F, 9, 43, 25, false),
        new Animal("Madison", Type.CAT, Sex.M, 3, 200, 16, true),
        new Animal("Bruno", Type.SPIDER, Sex.M, 3, 120, 2, false)
    ));

    public List<Animal> animalsWithErrors = new ArrayList<>(List.of(
        new Animal("Fedor", Type.DOG, Sex.F, -4, 20, 30, true),
        new Animal("Fluffy", Type.FISH, Sex.F, 1, -5, -1, false),
        new Animal("Bella Blue Whale", Type.CAT, Sex.F, 2, 0, 15, true),
        new Animal("Charlie", Type.SPIDER, Sex.M, 3, 6, 2, false)
    ));

    @Test
    @DisplayName("Проверка задания 1")
    void checkTask1() {
        //given
        List<Integer> expectAnswer = new ArrayList<>(List.of(5, 6, 7, 8, 10, 10, 16, 17, 20, 21, 22, 32));

        //when
        List<Animal> realAnswer = AnimalFunctions.task1(animals);

        //then
        for (int i = 0; i < expectAnswer.size(); ++i) {
            assertThat(realAnswer.get(i).height()).isEqualTo(expectAnswer.get(i));
        }
    }

    @Test
    @DisplayName("Проверка задания 2")
    void checkTask2() {
        //given
        List<Integer> expectAnswer = new ArrayList<>(List.of(35, 30, 25, 23));

        //when
        List<Animal> realAnswer = AnimalFunctions.task2(animals, 4);

        //then
        for (int i = 0; i < expectAnswer.size(); ++i) {
            assertThat(realAnswer.get(i).weight()).isEqualTo(expectAnswer.get(i));
        }
    }

    @Test
    @DisplayName("Проверка задания 3")
    void checkTask3() {
        //given
        Map<Type, Long> expectAnswer = new HashMap<>();
        expectAnswer.put(Type.BIRD, 2L);
        expectAnswer.put(Type.DOG, 2L);
        expectAnswer.put(Type.SPIDER, 2L);
        expectAnswer.put(Type.CAT, 3L);
        expectAnswer.put(Type.FISH, 3L);

        //when
        Map<Type, Long> realAnswer = AnimalFunctions.task3(animals);

        //then
        for (Map.Entry animal : realAnswer.entrySet()) {
            assertThat(animal.getValue()).isEqualTo(expectAnswer.get(animal.getKey()));
        }
    }

    @Test
    @DisplayName("Проверка задания 4")
    void checkTask4() {
        //given
        String expectAnswer = "Rocky Mountain Lion";

        //when
        Animal realAnswer = AnimalFunctions.task4(animals);

        //then
        assertThat(realAnswer.name()).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 5")
    void checkTask5() {
        //given
        Sex expectAnswer = Sex.F;

        //when
        Sex realAnswer = AnimalFunctions.task5(animals);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 6")
    void checkTask6() {
        //given
        Map<Type, String> expectAnswer = new HashMap<>();
        expectAnswer.put(Type.FISH, "Rocky Mountain Lion");
        expectAnswer.put(Type.BIRD, "Lucy");
        expectAnswer.put(Type.DOG, "Rocky");
        expectAnswer.put(Type.SPIDER, "Lola");
        expectAnswer.put(Type.CAT, "Oliver Owl");

        //when
        Map<Type, Animal> realAnswer = AnimalFunctions.task6(animals);

        //then
        for (Map.Entry animal : realAnswer.entrySet()) {
            Animal tmpAnimal = (Animal) animal.getValue();
            assertThat(tmpAnimal.name()).isEqualTo(expectAnswer.get(animal.getKey()));
        }
    }

    @Test
    @DisplayName("Проверка задания 7")
    void checkTask7() {
        //given
        String expectAnswer = "Lola";

        //when
        Animal realAnswer = AnimalFunctions.task7(animals);

        //then
        assertThat(realAnswer.name()).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 8")
    void checkTask8() {
        //given
        String expectAnswer = "Rocky Mountain Lion";

        //when
        Optional<Animal> realAnswer = AnimalFunctions.task8(animals, 10);

        //then
        assertThat(realAnswer.get().name()).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 9")
    void checkTask9() {
        //given
        int expectAnswer = 40;

        //when
        int realAnswer = AnimalFunctions.task9(animals);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 10")
    void checkTask10() {
        //given
        Set<String> expectAnswer = new HashSet<>(List.of("Fluffy", "Bella Blue Whale", "Charlie", "Luna"
            , "Oliver Owl", "Daisy", "Rocky", "Lucy", "Sophie", "Lola", "Rocky Mountain Lion"));

        //when
        List<Animal> realAnswer = AnimalFunctions.task10(animals);
        Set<String> realAnswerSetStrings = realAnswer.stream()
            .map(Animal::name)
            .collect(Collectors.toSet());

        //then
        assertThat(realAnswerSetStrings).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 11")
    void checkTask11() {
        //given
        Set<String> expectAnswer = new HashSet<>(List.of("Madison"));

        //when
        List<Animal> realAnswer = AnimalFunctions.task11(animals2);
        Set<String> realAnswerSetStrings = realAnswer.stream()
            .map(Animal::name)
            .collect(Collectors.toSet());

        //then
        assertThat(realAnswerSetStrings).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 12")
    void checkTask12() {
        //given
        int expectAnswer = 6;

        //when
        int realAnswer = AnimalFunctions.task12(animals);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 13")
    void checkTask13() {
        //given
        Set<String> expectAnswer = new HashSet<>(List.of("Bella Blue Whale", "Rocky Mountain Lion"));

        //when
        List<Animal> realAnswer = AnimalFunctions.task13(animals);
        Set<String> realAnswerSetStrings = realAnswer.stream()
            .map(Animal::name)
            .collect(Collectors.toSet());

        //then
        assertThat(realAnswerSetStrings).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 14")
    void checkTask14() {
        //given
        boolean expectAnswer = true;

        //when
        boolean realAnswer = AnimalFunctions.task14(animals, 10);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 15")
    void checkTask15() {
        //given
        int expectAnswer = 5;

        //when
        int realAnswer = AnimalFunctions.task15(animals, 10, 15);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 16")
    void checkTask16() {
        //given
        List<String> expectAnswer = new ArrayList<>(List.of("Oliver Owl", "Bella Blue Whale",
            "Sophie", "Rocky", "Fedor", "Lucy", "Luna", "Rocky Mountain Lion", "Daisy",
            "Fluffy", "Charlie", "Lola"
        ));

        //when
        List<Animal> realAnswer = AnimalFunctions.task16(animals);

        //then
        for (int i = 0; i < realAnswer.size(); ++i) {
            assertThat(realAnswer.get(i).name()).isEqualTo(expectAnswer.get(i));
        }
    }

    @Test
    @DisplayName("Проверка задания 17")
    void checkTask17() {
        //given
        boolean expectAnswer = false;

        //when
        boolean realAnswer = AnimalFunctions.task17(animals);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 18")
    void checkTask18() {
        //given
        String expectAnswer = "Coco";

        //when
        Animal realAnswer = AnimalFunctions.task18(animals, animals2);

        //then
        assertThat(realAnswer.name()).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка задания 19")
    void checkTask19() {
        //given
        Set<String> expectAnswer = new HashSet<>(List.of("Fedor", "Fluffy", "Bella Blue Whale"));

        //when
        Map<String, Set<ValidationError>> realAnswer = AnimalFunctions.task19(animalsWithErrors);
        Set<String> realAnswerSetStrings = new HashSet<>(realAnswer.keySet());

        //then
        assertThat(realAnswerSetStrings).isEqualTo(expectAnswer); // проверяем только имена
    }

    @Test
    @DisplayName("Проверка задания 20")
    void checkTask20() {
        //given
        Map<String, String> expectAnswer = new HashMap<>();
        expectAnswer.put("Fedor", "age error");
        expectAnswer.put("Fluffy", "height error, weight error");
        expectAnswer.put("Bella Blue Whale", "height error");

        //when
        Map<String, String> realAnswer = AnimalFunctions.task20(animalsWithErrors);

        //then
        for (Map.Entry animal : realAnswer.entrySet()) {
            List<String> realResults = new ArrayList<>(Arrays.asList(animal.getValue().toString().split(", ")));
            List<String> expectResults = new ArrayList<>(Arrays.asList(expectAnswer.get(animal.getKey()).split(", ")));
            for (String errorName : expectResults) {
                assertThat(realResults.contains(errorName)).isTrue();
            }
        }
    }
}
