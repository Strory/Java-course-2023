package edu.hw3;

import edu.hw3.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка числа 1")
    void checkOne() {
        //given
        int inputValue = 1;
        String expectAnswer = "I";
        Task4 numbToRoman = new Task4();

        //when
        String realAnswer = numbToRoman.numbToRoman(inputValue);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка числа 39")
    void checkThirtyNine() {
        //given
        int inputValue = 39;
        String expectAnswer = "XXXIX";
        Task4 numbToRoman = new Task4();

        //when
        String realAnswer = numbToRoman.numbToRoman(inputValue);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка числа 100")
    void checkOneHundred() {
        //given
        int inputValue = 100;
        String expectAnswer = "C";
        Task4 numbToRoman = new Task4();

        //when
        String realAnswer = numbToRoman.numbToRoman(inputValue);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка числа 703")
    void checkSevenHundredThree() {
        //given
        int inputValue = 703;
        String expectAnswer = "DCCIII";
        Task4 numbToRoman = new Task4();

        //when
        String realAnswer = numbToRoman.numbToRoman(inputValue);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка числа 3999")
    void checkThreeThousandNineHundredNinetyNine() {
        //given
        int inputValue = 3999;
        String expectAnswer = "MMMCMXCIX";
        Task4 numbToRoman = new Task4();

        //when
        String realAnswer = numbToRoman.numbToRoman(inputValue);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }
}
