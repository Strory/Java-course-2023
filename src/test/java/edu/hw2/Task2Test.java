package edu.hw2;

import edu.hw2.task1.Expr;
import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка площади прямоугольника")
    void checkRectangleArea() {
        //given
        double expectedAnswer = 20 * 40;

        //when
        var realAnswer = new Rectangle(20, 40).area();

        //then
        assertThat(realAnswer).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Проверка площади прямоугольника")
    void checkSquareArea() {
        //given
        double expectedAnswer = 30 * 30;

        //when
        var realAnswer = new Square(30).area();

        //then
        assertThat(realAnswer).isEqualTo(expectedAnswer);
    }
}
