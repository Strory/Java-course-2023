package edu.hw1;

public class Task6 {
    public static void main(String[] args) {
        System.out.println(K(6554));
    }

    public static int K(int numb) {
        if (numb == 6174) {
            return 0;
        } else {
            int[] digits = makeDigitsArray(numb);
            int n1 = sortAscending(digits);
            int n2 = sortDescending(digits);
            int nextNumber = (n1 > n2) ? n1 - n2 : n2 - n1;
            return 1 + K(nextNumber);
        }
    }

    public static int sortAscending(int[] digits) {
        int i = 0;
        while (i < 3) {
            if (digits[i] > digits[i + 1]) {
                int tmp = digits[i + 1];
                digits[i + 1] = digits[i];
                digits[i] = tmp;
                if (i != 0) {
                    --i;
                } else {
                    ++i;
                }
            } else {
                ++i;
            }
        }
        int digit = 0;
        for (int j = 0; j < 4; ++j) {
            digit += digits[j] * myPow(10, 3 - j);
        }
        return digit;
    }

    public static int sortDescending(int[] digits) {
        int i = 0;
        while (i < 3) {
            if (digits[i] < digits[i + 1]) {
                int tmp = digits[i + 1];
                digits[i + 1] = digits[i];
                digits[i] = tmp;
                if (i != 0) {
                    --i;
                } else {
                    ++i;
                }
            } else {
                ++i;
            }
        }
        int digit = 0;
        for (int j = 0; j < 4; ++j) {
            digit += digits[j] * myPow(10, 3 - j);
        }
        return digit;
    }

    public static int[] makeDigitsArray(int numb) {
        int[] digits = new int[4];
        for (int i = 0; i < 4; ++i) {
            int divider = myPow(10, 3 - i);
            digits[i] = numb / divider;
            numb %= divider;
        }
        return digits;
    }

    public static int myPow(int n, int pow) {
        int res = 1;
        for (int i = 0; i < pow; ++i) {
            res *= n;
        }
        return res;
    }
}
