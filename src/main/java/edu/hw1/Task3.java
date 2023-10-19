package edu.hw1;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        return (min(a1) > min(a2) && max(a1) < max(a2));
    }

    public static int min(int[] m) {
        int minElement = m[0];
        for (int element : m) {
            if (element < minElement) {
                minElement = element;
            }
        }
        return minElement;
    }

    public static int max(int[] m) {
        int maxElement = m[0];
        for (int elem : m) {
            if (elem > maxElement) {
                maxElement = elem;
            }
        }
        return maxElement;
    }
}

