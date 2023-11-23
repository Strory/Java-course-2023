package edu.hw7;

import edu.hw7.task1.Task1;
import edu.hw7.task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка правильного значения счетчика")
    void checkCorrectCounter() {
        //expect
        assertThat(200_000).isEqualTo(Task1.incrementer());
    }
}
