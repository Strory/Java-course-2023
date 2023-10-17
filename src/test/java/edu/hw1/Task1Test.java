package edu.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("Проверка правильного преобразования корректной строки")
    void checkCorrectString() {
        //given
        String inputString = "3213:22";

        //when
        int realAnswer = Task1.lenVideo(inputString);

        //then
        assertThat(realAnswer).isEqualTo(3213 * 60 + 22);
    }

    @Test
    @DisplayName("Проверка превышенного числа секунд")
    void checkExceededSeconds() {
        //given
        String inputString = "213:324";

        //when
        int realAnswer = Task1.lenVideo(inputString);

        //then
        assertThat(realAnswer).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка, если число секунд 59")
    void checkIf59Sec() {
        //given
        String inputString = "0:59";

        //when
        int realAnswer = Task1.lenVideo(inputString);

        //then
        assertThat(realAnswer).isEqualTo(59);
    }

    @Test
    @DisplayName("Проверка, если число секунд 60")
    void checkIf60Sec() {
        //given
        String inputString = "0:60";

        //when
        int realAnswer = Task1.lenVideo(inputString);

        //then
        assertThat(realAnswer).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка, если секунды отрицательные")
    void checkIfNegativeSec() {
        //given
        String inputString = "0:-21";

        //when
        int realAnswer = Task1.lenVideo(inputString);

        //then
        assertThat(realAnswer).isEqualTo(-1);
    }

    @Test
    @DisplayName("Проверка, если минуты отрицательные")
    void checkIfNegativeMin() {
        //given
        String inputString = "-5:21";

        //when
        int realAnswer = Task1.lenVideo(inputString);

        //then
        assertThat(realAnswer).isEqualTo(-1);
    }
}
