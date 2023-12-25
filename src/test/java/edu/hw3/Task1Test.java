package edu.hw3;

import edu.hw3.task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Проверка преобразования строки, состоящей только из букв")
    void checkOnlyLetterString() {
        //given
        String inputString = "Hello";
        Task1 decoder = new Task1();

        //when
        String realAnswer = decoder.decodeString(inputString);

        //then
        assertThat(realAnswer).isEqualTo("Svool");
    }

    @Test
    @DisplayName("Проверка примера из задания")
    void checkExampleTask() {
        //given
        String inputString = "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler";
        Task1 decoder = new Task1();

        //when
        String realAnswer = decoder.decodeString(inputString);

        //then
        assertThat(realAnswer).isEqualTo("Zmb ullo xzm dirgv xlwv gszg " +
            "z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm " +
            "fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Проверка строки, которая не должна меняться")
    void checkShouldNotChange() {
        //given
        String inputString = "Выпьем за любовь!!!";
        Task1 decoder = new Task1();

        //when
        String realAnswer = decoder.decodeString(inputString);

        //then
        assertThat(realAnswer).isEqualTo("Выпьем за любовь!!!");
    }
}
