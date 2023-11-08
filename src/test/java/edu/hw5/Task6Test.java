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

public class Task6Test {
    @Test
    @DisplayName("Проверка примера из задания")
    void checkExampleFromTask() {
        //given
        String inputString = "achfdbaabgabcaabg";
        String inputSubString = "abc";

        //when
        boolean realAnswer = Task6.task6(inputString, inputSubString);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка найденного вхождения 1")
    void checkFoundSubString1() {
        //given
        String inputString = "Hello world!";
        String inputSubString = "world";

        //when
        boolean realAnswer = Task6.task6(inputString, inputSubString);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка найденного вхождения 2")
    void checkFoundSubString2() {
        //given
        String inputString = "Я помню чудное мгновенье: Передо мной явилась ты, Как мимолетное виденье," +
            "Как гений чистой красоты. В томленьях грусти безнадежной, В тревогах шумной суеты," +
            "Звучал мне долго голос нежный И снились милые черты.";
        String inputSubString = "голос нежный";

        //when
        boolean realAnswer = Task6.task6(inputString, inputSubString);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка ненайденного вхождения")
    void checkUnfoundSubString() {
        //given
        String inputString = "jfd;ghd4439jf;s";
        String inputSubString = "gh440";

        //when
        boolean realAnswer = Task6.task6(inputString, inputSubString);

        //then
        Assertions.assertFalse(realAnswer);
    }
}
