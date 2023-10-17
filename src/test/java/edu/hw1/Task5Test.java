package edu.hw1;

import edu.hw1.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("Проверка числа, у которого потомок является палиндромом")
    void check1() {
        //given
        int testNumber = 11211230;

        //when
        boolean realAnswer = Task5.isPalindromeDescendant(testNumber);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка числа, у которого потомок является палиндромом 2")
    void check2() {
        //given
        int testNumber = 23336014;

        //when
        boolean realAnswer = Task5.isPalindromeDescendant(testNumber);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка числа, которое не является палиндромом")
    void check3() {
        //given
        int testNumber = 21;

        //when
        boolean realAnswer = Task5.isPalindromeDescendant(testNumber);

        //then
        Assertions.assertFalse(realAnswer);
    }
}
