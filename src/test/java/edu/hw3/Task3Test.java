package edu.hw3;

import edu.hw3.task2.Task2;
import edu.hw3.task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Проверка строковых значений")
    void checkStringValues() {
        //given
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList("a", "bb", "a", "bb"));
        HashMap<String, Integer> expectAnswer = new HashMap<>();
        expectAnswer.put("a", 2);
        expectAnswer.put("bb", 2);

        //when
        HashMap<String, Integer> realAnswer = Task3.makeFrequencyMap(inputList);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка строковых значений2")
    void checkStringValues2() {
        //given
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList("код", "код", "код", "bug"));
        HashMap<String, Integer> expectAnswer = new HashMap<>();
        expectAnswer.put("код", 3);
        expectAnswer.put("bug", 1);

        //when
        HashMap<String, Integer> realAnswer = Task3.makeFrequencyMap(inputList);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка числовых значений")
    void checkIntegerValues() {
        //given
        ArrayList<Integer> inputList = new ArrayList<>(Arrays.asList(1, 1, 2, 2));
        HashMap<Integer, Integer> expectAnswer = new HashMap<>();
        expectAnswer.put(1, 2);
        expectAnswer.put(2, 2);

        //when
        HashMap<Integer, Integer> realAnswer = Task3.makeFrequencyMap(inputList);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }
}
