package edu.hw2;

import static org.assertj.core.api.Assertions.assertThat;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("Проверка Constant")
    void checkConstant() {
        //given
        double testValue = 23;

        //when
        var realAnswer = new Expr.Constant(testValue).evaluate();

        //then
        assertThat(realAnswer).isEqualTo(testValue);
    }

    @Test
    @DisplayName("Проверка Negate")
    void checkNegate() {
        //given
        double testValue = 17;

        //when
        var realAnswer = new Expr.Negate(testValue).evaluate();

        //then
        assertThat(-realAnswer).isEqualTo(testValue);
    }

    @Test
    @DisplayName("Проверка Exponent")
    void checkExponent() {
        //given
        double testValue = 5;
        int testPow = 4;

        //when
        var realAnswer = new Expr.Exponent(testValue, testPow).evaluate();
        int expectedAnswer = 625;

        //then
        assertThat(realAnswer).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Проверка Addition")
    void checkAddition() {
        //given
        double testValue1 = 91.2;
        double testValue2 = 4.3;

        //when
        var realAnswer = new Expr.Addition(testValue1, testValue2).evaluate();

        //then
        assertThat(realAnswer).isEqualTo(testValue1 + testValue2);
    }

    @Test
    @DisplayName("Проверка Multiplication")
    void checkMultiplication() {
        //given
        double testValue1 = 21;
        double testValue2 = 20;

        //when
        var realAnswer = new Expr.Multiplication(testValue1, testValue2).evaluate();

        //then
        assertThat(realAnswer).isEqualTo(testValue1 * testValue2);
    }

    @Test
    @DisplayName("Проверка примера из задания")
    void checkLongExample() {
        //given

        //when
        var two = new Expr.Constant(2).evaluate();
        var four = new Expr.Constant(4).evaluate();
        var negOne = new Expr.Negate(new Expr.Constant(1).evaluate()).evaluate();
        var sumTwoFour = new Expr.Addition(two, four).evaluate();
        var mult = new Expr.Multiplication(sumTwoFour, negOne).evaluate();
        var exp = new Expr.Exponent(mult, 2).evaluate();
        var res = new Expr.Addition(exp, new Expr.Constant(1).evaluate()).evaluate();

        double expectedAnswer = 37;

        //then
        assertThat(res).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Проверка нулевой степени")
    void checkZeroPow() {
        //given
        double two = 2;
        int pow = 0;

        //when
        var realAnswer = new Expr.Exponent(two, pow).evaluate();

        double expectedAnswer = 1;

        //then
        assertThat(realAnswer).isEqualTo(expectedAnswer);
    }
}
