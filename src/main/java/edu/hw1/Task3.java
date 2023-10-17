package edu.hw1;

public class Task3 {
    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};

        System.out.println(isNestable(a1, a2));
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        return (min(a1) > min(a2) && max(a1) < max(a2));
    }

    public static int min(int[] m) {
        int min_el = m[0];
        for (int elem : m) {
            if (elem < min_el) {
                min_el = elem;
            }
        }
        return min_el;
    }

    public static int max(int[] m) {
        int max_el = m[0];
        for (int elem : m) {
            if (elem > max_el) {
                max_el = elem;
            }
        }
        return max_el;
    }
}

