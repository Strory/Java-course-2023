package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Project1Test {
    @Test
    @DisplayName("Проверка, если все ответы, являются попаданиями")
    void checkAllHitAnswers() {
        //given
        String[] commands = {"v", "l", "o", "i", "n"};
        String expectAnswer = "22224";

        //when
        String realAnswer = TestCommandController.commandSender(commands);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка, если встречаются промахи")
    void checkIfHaveMistakes() {
        //given
        String[] commands = {"v", "y", "l", "x", "o", "i", "n"};
        String expectAnswer = "2323224";

        //when
        String realAnswer = TestCommandController.commandSender(commands);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка, некорректного ввода, а также преждевременного выхода")
    void checkIncorrectInputAndQuit() {
        //given
        String[] commands = {"v", "hello", "u", "", "i", "quit"};
        String expectAnswer = "213120";

        //when
        String realAnswer = TestCommandController.commandSender(commands);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка, поражения")
    void checkLost() {
        //given
        String[] commands = {"v", "hello", "u", "", "i", "x", "j", "t", "r"};
        String expectAnswer = "213123335";

        //when
        String realAnswer = TestCommandController.commandSender(commands);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }
}
