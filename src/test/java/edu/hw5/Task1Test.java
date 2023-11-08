package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка одинаковых интервалов")
    void checkSameDurations() {
        //given
        String[] durations = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:52",
            "2022-03-12, 20:20 - 2022-03-12, 23:52",
            "2022-03-12, 20:20 - 2022-03-12, 23:52",
        };
        String expectAnswer = "3ч 32м";

        //when
        String realAnswer = Task1.getAverageTimeSession(durations);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка разных интервалов")
    void checkDifferentDurations() {
        //given
        String[] durations = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:52",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2022-01-11, 20:12 - 2022-01-12, 06:30",
        };
        String expectAnswer = "5ч 53м";
        ;

        //when
        String realAnswer = Task1.getAverageTimeSession(durations);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка интервалов из задания")
    void checkDurationsFromTask() {
        //given
        String[] durations = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
        };
        String expectAnswer = "3ч 40м";
        ;

        //when
        String realAnswer = Task1.getAverageTimeSession(durations);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }
}
