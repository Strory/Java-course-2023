package edu.hw1;

public class Task7 {
    public static void main(String[] args) {
        System.out.println(rotateRight(6621, 2));
        System.out.println(rotateLeft(17, 3));
    }

    public static int rotateRight(int n, int shift) {
        int[] bits = bitSplitting(n);
        int[] rotateBits = new int[bits.length];
        shift %= bits.length;
        for (int i = 0; i < bits.length; ++i) {
            if ((i + shift) > (bits.length - 1)) {
                rotateBits[shift - bits.length + i] = bits[i];
            } else {
                rotateBits[i + shift] = bits[i];
            }
        }
        int number = 0;
        for (int i = 0; i < rotateBits.length; ++i) {
            if (rotateBits[i] != 0) {
                number += myPow(2, rotateBits.length - i - 1);
            }
        }
        return number;
    }

    public static int rotateLeft(int n, int shift) {
        int[] bits = bitSplitting(n);
        int[] rotateBits = new int[bits.length];
        shift %= bits.length;
        for (int i = 0; i < bits.length; ++i) {
            if ((i - shift) < 0) {
                rotateBits[bits.length - shift + i] = bits[i];
            } else {
                rotateBits[i - shift] = bits[i];
            }
        }
        int number = 0;
        for (int i = 0; i < rotateBits.length; ++i) {
            if (rotateBits[i] != 0) {
                number += myPow(2, rotateBits.length - i - 1);
            }
        }
        return number;
    }

    public static int[] bitSplitting(int n) {
        int countBits = binaryNumberOrder(n);
        int[] bits = new int[countBits];
        for (int i = 0; i < countBits; ++i) {
            bits[countBits - 1 - i] = n % 2;
            n /= 2;
        }
        return bits;
    }

    public static int binaryNumberOrder(int numb) {
        int pow2 = 0;
        int twoToThePower = 1;
        while (numb >= twoToThePower) {
            ++pow2;
            twoToThePower = myPow(2, pow2);
        }
        return pow2;
    }

    public static int myPow(int n, int pow) {
        int res = 1;
        for (int i = 0; i < pow; ++i) {
            res *= n;
        }
        return res;
    }
}
