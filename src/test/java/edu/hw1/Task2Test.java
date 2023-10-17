package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка обычного числа")
    void checkOrdinaryNumber() {
        //given
        int number = 54353;

        //when
        int realAnswer = Task2.countDigits(number);

        //then
        assertThat(realAnswer).isEqualTo(5);
    }
}
