package edu.hw1;

public class Task2 {
    public static void main(String[] args) {
        System.out.println(countDigits(101));
    }

    public static int countDigits(int n) {
        int count = 1;
        while (n > 9) {
            n /= 10;
            ++count;
        }
        return count;
    }
}
