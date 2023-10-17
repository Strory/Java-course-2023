package edu.hw1;

import edu.hw1.Task6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Проверка обычного числа")
    void check1() {
        //given
        int testNumber = 6621;

        //when
        int realAnswer = Task6.K(testNumber);

        //then
        assertThat(realAnswer).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка обычного числа 2")
    void check2() {
        //given
        int testNumber = 6554;

        //when
        int realAnswer = Task6.K(testNumber);

        //then
        assertThat(realAnswer).isEqualTo(4);
    }

    @Test
    @DisplayName("Проверка числа 3")
    void check3() {
        //given
        int testNumber = 1234;

        //when
        int realAnswer = Task6.K(testNumber);

        //then
        assertThat(realAnswer).isEqualTo(3);
    }
}
