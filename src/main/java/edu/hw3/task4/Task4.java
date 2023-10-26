package edu.hw3.task4;

import java.util.HashMap;

public class Task4 {
    public HashMap<Integer, String> numbers;
    private final int maxPower = 3;

    public Task4() {
        createMapRomanNumbers();
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    public String numbToRoman(int numb) {
        StringBuilder romanNumber = new StringBuilder();
        if (numb > 3999 || numb < 0) {
            return null;
        }

        int pow = maxPower;
        while (numb > 0) {
            romanNumber.append(uni(numb / (myPow(10, pow)), pow));
            numb %= myPow(10, pow);
            --pow;
        }
        return romanNumber.toString();
    }

    @SuppressWarnings({"ParameterAssignment", "MagicNumber"})
    public String uni(int digit, int power) {
        StringBuilder romanDigit = new StringBuilder();
        if (digit == 9) {
            romanDigit.append(numbers.get(9 * myPow(10, power)));
            digit -= 9;
        } else if (digit > 4) {
            romanDigit.append(numbers.get(5 * myPow(10, power)));
            digit -= 5;
        } else if (digit == 4) {
            romanDigit.append(numbers.get(4 * myPow(10, power)));
            digit -= 4;
        }
        while (digit > 0) {
            romanDigit.append(numbers.get(myPow(10, power)));
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
