package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double x) implements Expr {
        public double evaluate() {
            return x;
        }
    }

    public record Negate(double x) implements Expr {
        public double evaluate() {
            return -x;
        }
    }

    public record Exponent(double x, int pow) implements Expr {
        public double evaluate() {
            double res = 1;
            for (int i = 0; i < pow; ++i) {
                res *= x;
            }
            return res;
        }
    }

    public record Addition(double x1, double x2) implements Expr {
        public double evaluate() {
            return x1 + x2;
        }
    }

    public record Multiplication(double x1, double x2) implements Expr {
        public double evaluate() {
            return x1 * x2;
        }
    }
}
