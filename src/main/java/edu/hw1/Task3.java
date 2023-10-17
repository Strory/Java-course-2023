package edu.hw1;

public class Task3 {
    private Task3() {
    }
//    public static void main(String[] args) {
//        int[] a1 = {1, 2, 3, 4};
//        int[] a2 = {2, 3};
//
//        System.out.println(isNestable(a1, a2));
//    }

    public static boolean isNestable(int[] a1, int[] a2) {
        return (min(a1) > min(a2) && max(a1) < max(a2));
    }

    public static int min(int[] m) {
        int minEl = m[0];
        for (int elem : m) {
            if (elem < minEl) {
                minEl = elem;
            }
        }
        return minEl;
    }

    public static int max(int[] m) {
        int maxEl = m[0];
        for (int elem : m) {
            if (elem > maxEl) {
                maxEl = elem;
            }
        }
        return maxEl;
    }
}

