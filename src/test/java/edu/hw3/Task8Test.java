package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Проверка итератора")
    void checkIterator() {
        //given
        BackwardIterator<Integer> myIter = new BackwardIterator<>(List.of(1, 2, 3));

        //expect
        int checkAnswer = 3;
        while (myIter.hasNext()) {
            assertThat(myIter.next()).isEqualTo(checkAnswer);
            --checkAnswer;
        }
    }
}
