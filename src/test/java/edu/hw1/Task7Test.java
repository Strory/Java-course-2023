package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка сдвига вправо 1")
    void checkRotateRight1() {
        //given
        int testNumber = 8;
        int shift = 1;

        //when
        int realAnswer = Task7.rotateRight(testNumber, shift);

        //then
        assertThat(realAnswer).isEqualTo(4);
    }

    @Test
    @DisplayName("Проверка сдвига вправо 2")
    void checkRotateRight2() {
        //given
        int testNumber = 4;
        int shift = 2;

        //when
        int realAnswer = Task7.rotateRight(testNumber, shift);

        //then
        assertThat(realAnswer).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка сдвига влево 1")
    void checkRotateLeft1() {
        //given
        int testNumber = 16;
        int shift = 1;

        //when
        int realAnswer = Task7.rotateLeft(testNumber, shift);

        //then
        assertThat(realAnswer).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка сдвига влево 2")
    void checkRotateLeft2() {
        //given
        int testNumber = 17;
        int shift = 2;

        //when
        int realAnswer = Task7.rotateLeft(testNumber, shift);

        //then
        assertThat(realAnswer).isEqualTo(6);
    }
}
