package edu.hw3;

//import org.junit.jupiter.api.Assertions;

import edu.hw3.task1.Task1;
import edu.hw3.task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка одиночных скобок")
    void checkSingleBrackets() {
        //given
        String inputString = "()()()";
        ArrayList<String> expectAnswer = new ArrayList<>(Arrays.asList("()", "()", "()"));

        //when
        ArrayList<String> realAnswer = Task2.splitBrackets(inputString);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка одной группы скобок")
    void checkSingleGroupOfBrackets() {
        //given
        String inputString = "((()))";
        ArrayList<String> expectAnswer = new ArrayList<>(Arrays.asList("((()))"));

        //when
        ArrayList<String> realAnswer = Task2.splitBrackets(inputString);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка сложного выражения")
    void checkComplexExpression() {
        //given
        String inputString = "((()))(())()()(()())";
        ArrayList<String> expectAnswer = new ArrayList<>(Arrays.asList("((()))", "(())", "()", "()", "(()())"));

        //when
        ArrayList<String> realAnswer = Task2.splitBrackets(inputString);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка сложного выражения 2")
    void checkComplexExpression2() {
        //given
        String inputString = "((())())(()(()()))";
        ArrayList<String> expectAnswer = new ArrayList<>(Arrays.asList("((())())", "(()(()()))"));

        //when
        ArrayList<String> realAnswer = Task2.splitBrackets(inputString);

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }
}
