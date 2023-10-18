package edu.hw2;

import edu.hw2.task4.CallingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка возврата корректной строки")
    void checkConstant() {
        //given
        String correctString = "edu.hw2.task4.CallingInfo callingInfo";

        //when
        String realAnswer = new CallingInfo().callingInfo();

        //then
        assertThat(realAnswer).isEqualTo(correctString);
    }
}

