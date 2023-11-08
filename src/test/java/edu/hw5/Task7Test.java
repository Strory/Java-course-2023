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

public class Task7Test {
    @Test
    @DisplayName("Проверка корректной строки 1, задача 1")
    void checkCorrectString1T1() {
        //given
        String inputString = "1001010001100";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "1");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 1")
    void checkCorrectString2T1() {
        //given
        String inputString = "01011110";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "1");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 1")
    void checkIncorrectString1T1() {
        //given
        String inputString = "0110110";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "1");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 1")
    void checkIncorrectString2T1() {
        //given
        String inputString = "10";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "1");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 2")
    void checkCorrectString1T2() {
        //given
        String inputString = "101";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "2");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 2")
    void checkCorrectString2T2() {
        //given
        String inputString = "00001110101100110";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "2");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 2")
    void checkIncorrectString1T2() {
        //given
        String inputString = "001100111";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "2");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 2")
    void checkIncorrectString2T2() {
        //given
        String inputString = "10011100";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "2");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 3")
    void checkCorrectString1T3() {
        //given
        String inputString = "110";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "3");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 3")
    void checkCorrectString2T3() {
        //given
        String inputString = "0";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "3");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 3")
    void checkIncorrectString1T3() {
        //given
        String inputString = "";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "3");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 3")
    void checkIncorrectString2T3() {
        //given
        String inputString = "101110";

        //when
        boolean realAnswer = Task7.tusk7(inputString, "3");

        //then
        Assertions.assertFalse(realAnswer);
    }
}
