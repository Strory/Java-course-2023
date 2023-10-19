package edu.hw1;

public class Task2 {
    private Task2() {
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    public static int countDigits(int n) {
        int count = 1;
        while (n > 9) {
            n /= 10;
            ++count;
        }
        return count;
    }
}
