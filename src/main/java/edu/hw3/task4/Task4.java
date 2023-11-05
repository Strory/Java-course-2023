package edu.hw3.task4;

import java.util.HashMap;

public class Task4 {
    public HashMap<Integer, String> numbers;
    private final int maxPower = 3;

    public Task4() {
        createMapRomanNumbers();
    }

    @SuppressWarnings({"ParameterAssignment"})
    public String numbToRoman(int numb) {
        final int THREE_THOUSAND_NINE_HUNDRED_NINETY_NINE = 3999;
        final int TEN = 10;
        StringBuilder romanNumber = new StringBuilder();
        if (numb > THREE_THOUSAND_NINE_HUNDRED_NINETY_NINE || numb < 0) {
            return null;
        }

        int pow = maxPower;
        while (numb > 0) {
            romanNumber.append(getRomanDigit(numb / (myPow(TEN, pow)), pow));
            numb %= myPow(TEN, pow);
            --pow;
        }
        return romanNumber.toString();
    }

    @SuppressWarnings({"ParameterAssignment"})
    public String getRomanDigit(int digit, int power) {
        final int FOUR = 4;
        final int FIVE = 5;
        final int NINE = 9;
        final int TEN = 10;
        StringBuilder romanDigit = new StringBuilder();
        if (digit == NINE) {
            romanDigit.append(numbers.get(NINE * myPow(TEN, power)));
            digit -= NINE;
        } else if (digit > FOUR) {
            romanDigit.append(numbers.get(FIVE * myPow(TEN, power)));
            digit -= FIVE;
        } else if (digit == FOUR) {
            romanDigit.append(numbers.get(FOUR * myPow(TEN, power)));
            digit -= FOUR;
        }
        while (digit > 0) {
            romanDigit.append(numbers.get(myPow(TEN, power)));
            --digit;
        }
        return romanDigit.toString();
    }

    @SuppressWarnings("MagicNumber")
    public void createMapRomanNumbers() {
        numbers = new HashMap<>();
        numbers.put(1, "I");
        numbers.put(4, "IV");
        numbers.put(5, "V");
        numbers.put(9, "IX");
        numbers.put(10, "X");
        numbers.put(40, "XL");
        numbers.put(50, "L");
        numbers.put(90, "XC");
        numbers.put(100, "C");
        numbers.put(400, "CD");
        numbers.put(500, "D");
        numbers.put(900, "CM");
        numbers.put(1000, "M");
    }

    public static int myPow(int n, int pow) {
        int res = 1;
        for (int i = 0; i < pow; ++i) {
            res *= n;
        }
        return res;
    }
}
