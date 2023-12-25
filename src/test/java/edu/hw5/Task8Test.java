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

public class Task8Test {
    @Test
    @DisplayName("Проверка корректной строки 1, задача 1")
    void checkCorrectString1T1() {
        //given
        String inputString = "1001100";

        //when
        boolean realAnswer = Task8.task8(inputString, "1");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 1")
    void checkCorrectString2T1() {
        //given
        String inputString = "1";

        //when
        boolean realAnswer = Task8.task8(inputString, "1");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 1")
    void checkIncorrectString1T1() {
        //given
        String inputString = "10";

        //when
        boolean realAnswer = Task8.task8(inputString, "1");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 1")
    void checkIncorrectString2T1() {
        //given
        String inputString = "100110";

        //when
        boolean realAnswer = Task8.task8(inputString, "1");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 2")
    void checkCorrectString1T2() {
        //given
        String inputString = "01011";

        //when
        boolean realAnswer = Task8.task8(inputString, "2");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 2")
    void checkCorrectString2T2() {
        //given
        String inputString = "110101";

        //when
        boolean realAnswer = Task8.task8(inputString, "2");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 2")
    void checkIncorrectString1T2() {
        //given
        String inputString = "1101101";

        //when
        boolean realAnswer = Task8.task8(inputString, "2");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 2")
    void checkIncorrectString2T2() {
        //given
        String inputString = "01011101";

        //when
        boolean realAnswer = Task8.task8(inputString, "2");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 3")
    void checkCorrectString1T3() {
        //given
        String inputString = "01011101";

        //when
        boolean realAnswer = Task8.task8(inputString, "3");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 3")
    void checkCorrectString2T3() {
        //given
        String inputString = "01010011011110";

        //when
        boolean realAnswer = Task8.task8(inputString, "3");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 3")
    void checkIncorrectString1T3() {
        //given
        String inputString = "010100111110";

        //when
        boolean realAnswer = Task8.task8(inputString, "3");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 3")
    void checkIncorrectString2T3() {
        //given
        String inputString = "0101011110";

        //when
        boolean realAnswer = Task8.task8(inputString, "3");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 4")
    void checkCorrectString1T4() {
        //given
        String inputString = "01010";

        //when
        boolean realAnswer = Task8.task8(inputString, "4");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 4")
    void checkCorrectString2T4() {
        //given
        String inputString = "110";

        //when
        boolean realAnswer = Task8.task8(inputString, "4");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 4")
    void checkIncorrectString1T4() {
        //given
        String inputString = "11";

        //when
        boolean realAnswer = Task8.task8(inputString, "4");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 4")
    void checkIncorrectString2T4() {
        //given
        String inputString = "111";

        //when
        boolean realAnswer = Task8.task8(inputString, "4");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 5")
    void checkCorrectString1T5() {
        //given
        String inputString = "111";

        //when
        boolean realAnswer = Task8.task8(inputString, "5");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 5")
    void checkCorrectString2T5() {
        //given
        String inputString = "10111011";

        //when
        boolean realAnswer = Task8.task8(inputString, "5");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 5")
    void checkIncorrectString1T5() {
        //given
        String inputString = "10111001";

        //when
        boolean realAnswer = Task8.task8(inputString, "5");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 5")
    void checkIncorrectString2T5() {
        //given
        String inputString = "00101";

        //when
        boolean realAnswer = Task8.task8(inputString, "5");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 6")
    void checkCorrectString1T6() {
        //given
        String inputString = "0010";

        //when
        boolean realAnswer = Task8.task8(inputString, "6");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 6")
    void checkCorrectString2T6() {
        //given
        String inputString = "100";

        //when
        boolean realAnswer = Task8.task8(inputString, "6");

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 6")
    void checkIncorrectString1T6() {
        //given
        String inputString = "10";

        //when
        boolean realAnswer = Task8.task8(inputString, "6");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 6")
    void checkIncorrectString2T6() {
        //given
        String inputString = "1001";

        //when
        boolean realAnswer = Task8.task8(inputString, "6");

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка корректной строки 1, задача 7")
    void checkCorrectString1T7() {
        //given
        String inputString = "1001";

        //when
        boolean realAnswer1 = Task8.task8(inputString, "7_1");
        boolean realAnswer2 = Task8.task8(inputString, "7_2");

        //then
        Assertions.assertTrue(realAnswer1);
        Assertions.assertTrue(realAnswer2);
    }

    @Test
    @DisplayName("Проверка корректной строки 2, задача 7")
    void checkCorrectString2T7() {
        //given
        String inputString = "010000100101";

        //when
        boolean realAnswer1 = Task8.task8(inputString, "7_1");
        boolean realAnswer2 = Task8.task8(inputString, "7_2");

        //then
        Assertions.assertTrue(realAnswer1);
        Assertions.assertTrue(realAnswer2);
    }

    @Test
    @DisplayName("Проверка некорректной строки 1, задача 7")
    void checkIncorrectString1T7() {
        //given
        String inputString = "0100001001101";

        //when
        boolean realAnswer1 = Task8.task8(inputString, "7_1");
        boolean realAnswer2 = Task8.task8(inputString, "7_2");

        //then
        Assertions.assertFalse(realAnswer1);
        Assertions.assertFalse(realAnswer2);
    }

    @Test
    @DisplayName("Проверка некорректной строки 2, задача 7")
    void checkIncorrectString2T7() {
        //given
        String inputString = "11001";

        //when
        boolean realAnswer1 = Task8.task8(inputString, "7_1");
        boolean realAnswer2 = Task8.task8(inputString, "7_2");

        //then
        Assertions.assertFalse(realAnswer1);
        Assertions.assertFalse(realAnswer2);
    }
}
