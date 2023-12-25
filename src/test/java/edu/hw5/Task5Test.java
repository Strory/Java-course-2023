package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Проверка правильного знака 1")
    void checkCorrectSign1() {
        //given
        String inputString = "А123ВЕ777";

        //when
        boolean realAnswer = Task5.task5(inputString);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка правильного знака 2")
    void checkCorrectSign2() {
        //given
        String inputString = "О777ОО177";

        //when
        boolean realAnswer = Task5.task5(inputString);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка неправильного знака 1")
    void checkIncorrectSign1() {
        //given
        String inputString = "123АВЕ777";

        //when
        boolean realAnswer = Task5.task5(inputString);

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка неправильного знака 2")
    void checkIncorrectSign2() {
        //given
        String inputString = "А123ВГ77";

        //when
        boolean realAnswer = Task5.task5(inputString);

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка неправильного знака 3")
    void checkIncorrectSign3() {
        //given
        String inputString = "А123ВЕ7777";

        //when
        boolean realAnswer = Task5.task5(inputString);

        //then
        Assertions.assertFalse(realAnswer);
    }
}
