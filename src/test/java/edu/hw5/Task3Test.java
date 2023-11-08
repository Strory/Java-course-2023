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

public class Task3Test {
    @Test
    @DisplayName("Проверка даты без нулей в месяцах и годах")
    void checkDateNoZeros() {
        //given
        String inputString = "2003-2-5";
        Optional<LocalDate> expectAnswer = Optional.of(LocalDate.of(2003, 2, 5));

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 1")
    void checkDate1() {
        //given
        String inputString = "2020-10-10";
        Optional<LocalDate> expectAnswer = Optional.of(LocalDate.of(2020, 10, 10));

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 2")
    void checkDate2() {
        //given
        String inputString = "2020-12-2";
        Optional<LocalDate> expectAnswer = Optional.of(LocalDate.of(2020, 12, 2));

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 3")
    void checkDate3() {
        //given
        String inputString = "1/3/1976";
        Optional<LocalDate> expectAnswer = Optional.of(LocalDate.of(1976, 3, 1));

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 4")
    void checkDate4() {
        //given
        String inputString = "1/3/20";
        Optional<LocalDate> expectAnswer = Optional.of(LocalDate.of(2020, 3, 1));

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 5")
    void checkDate5() {
        //given
        String inputString = "tomorrow";
        LocalDate date = LocalDate.now().plusDays(1);
        Optional<LocalDate> expectAnswer = Optional.of(date);

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 6")
    void checkDate6() {
        //given
        String inputString = "today";
        LocalDate date = LocalDate.now();
        Optional<LocalDate> expectAnswer = Optional.of(date);

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 7")
    void checkDate7() {
        //given
        String inputString = "yesterday";
        LocalDate date = LocalDate.now().minusDays(1);
        Optional<LocalDate> expectAnswer = Optional.of(date);

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 8")
    void checkDate8() {
        //given
        String inputString = "1 day ago";
        LocalDate date = LocalDate.now().minusDays(1);
        Optional<LocalDate> expectAnswer = Optional.of(date);

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка даты 9")
    void checkDate9() {
        //given
        String inputString = "2234 days ago";
        LocalDate date = LocalDate.now().minusDays(2234);
        Optional<LocalDate> expectAnswer = Optional.of(date);

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка пустого Optional")
    void checkEmptyOptional() {
        //given
        String inputString = "23-15-10";
        Optional<LocalDate> expectAnswer = Optional.empty();

        //when
        Optional<LocalDate> realAnswer = Task3.parseDate(inputString);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }
}
