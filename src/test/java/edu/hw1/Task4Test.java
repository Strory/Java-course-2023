package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка обычной строки")
    void checkOrdinaryString1() {
        //given
        String testString = "оПомигети псаривьтс ртко!и";

        //when
        String realAnswer = Task4.fixString(testString);

        //then
        assertThat(realAnswer).isEqualTo("Помогите исправить строки!");
    }

    @Test
    @DisplayName("Проверка строки из одного символа")
    void checkOrdinaryString2() {
        //given
        String testString = "1";

        //when
        String realAnswer = Task4.fixString(testString);

        //then
        assertThat(realAnswer).isEqualTo("1");
    }
}
