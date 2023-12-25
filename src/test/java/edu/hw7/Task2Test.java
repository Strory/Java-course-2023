package edu.hw7;

import edu.hw7.task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка правильного значения факториала")
    void checkFactorial() {
        //expect
        assertThat(720).isEqualTo(Task2.getFactorial(6));
    }

    @Test
    @DisplayName("Проверка правильного значения факториала")
    void checkFactorial2() {
        //expect
        assertThat(3628800).isEqualTo(Task2.getFactorial(10));
    }
}
