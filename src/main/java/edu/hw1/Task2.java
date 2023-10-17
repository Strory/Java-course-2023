package edu.hw1;

public class Task2 {
    private Task2() {
    }

//    public static void main(String[] args) {
//        System.out.println(countDigits(101));
//    }
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
