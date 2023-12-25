package edu.hw5;

import edu.hw5.task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка невалидного пароля")
    void checkBadPass() {
        //given
        String inputString = "gfdgdtees4t123";

        //when
        boolean realAnswer = Task4.task4(inputString);

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка валидного пароля")
    void checkGoodPass() {
        //given
        String inputString = "gf#dte23";

        //when
        boolean realAnswer = Task4.task4(inputString);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка пустой строки")
    void checkEmptyString() {
        //given
        String inputString = "";

        //when
        boolean realAnswer = Task4.task4(inputString);

        //then
        Assertions.assertFalse(realAnswer);
    }

}
