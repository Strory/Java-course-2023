package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка 1925 года")
    void check1925Year() {
        //given
        int year = 1925;
        List<LocalDate> expectAnswer = new ArrayList<>(List.of(
            LocalDate.of(year, 2, 13),
            LocalDate.of(year, 3, 13),
            LocalDate.of(year, 11, 13)
        ));

        //when
        List<LocalDate> realAnswer = Task2.getFridays(year);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка 2024 года")
    void check2024Year() {
        //given
        int year = 2024;
        List<LocalDate> expectAnswer = new ArrayList<>(List.of(
            LocalDate.of(year, 9, 13),
            LocalDate.of(year, 12, 13)
        ));

        //when
        List<LocalDate> realAnswer = Task2.getFridays(year);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка следующей ближайшей пятницы после 13 числа в месяце")
    void checkNextFridayAfterThirteen() {
        //given
        LocalDate inputDate = LocalDate.of(1925, 5, 15);
        LocalDate expectAnswer = LocalDate.of(1925, 11, 13);

        //when
        LocalDate realAnswer = Task2.getNextFridayThirteen(inputDate);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка следующей ближайшей пятницы до 13 числа в месяце")
    void checkNextFridayBeforeThirteen() {
        //given
        LocalDate inputDate = LocalDate.of(2024, 7, 3);
        LocalDate expectAnswer = LocalDate.of(2024, 9, 13);

        //when
        LocalDate realAnswer = Task2.getNextFridayThirteen(inputDate);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }
}
