package edu.hw1;

import edu.hw1.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class Task3Test {
    @Test
    @DisplayName("Проверка, если нельзя вложить один массив в другой")
    void checkOrdinaryArrays1() {
        //given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};

        //when
        boolean realAnswer = Task3.isNestable(a1, a2);

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка, если можно вложить один массив в другой")
    void checkOrdinaryArrays2() {
        //given
        int[] a1 = {2, 2, 3, 4};
        int[] a2 = {1, 3, 6};

        //when
        boolean realAnswer = Task3.isNestable(a1, a2);

        //then
        Assertions.assertTrue(realAnswer);
    }

    @Test
    @DisplayName("Проверка, если нельзя вложить, т.к. не выполняется минимум")
    void checkOrdinaryArrays3() {
        //given
        int[] a1 = {1, 2, 2, 3, 4};
        int[] a2 = {1, 3, 6};

        //when
        boolean realAnswer = Task3.isNestable(a1, a2);

        //then
        Assertions.assertFalse(realAnswer);
    }

    @Test
    @DisplayName("Проверка, если нельзя вложить, т.к. не выполняется максимум")
    void checkOrdinaryArrays4() {
        //given
        int[] a1 = {1, 2, 2, 3, 4, 7};
        int[] a2 = {0, 3, 6};

        //when
        boolean realAnswer = Task3.isNestable(a1, a2);

        //then
        Assertions.assertFalse(realAnswer);
    }
}
