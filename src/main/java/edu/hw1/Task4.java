package edu.hw1;

public class Task4 {
    public static void main(String[] args) {
        String test = "214365";
        System.out.println(fixString(test));
    }

    public static String fixString(String s) {
        StringBuilder new_str = new StringBuilder();
        for (int i = 0; i < s.length() / 2; ++i) {
            char symb1 = s.charAt(i * 2 + 1);
            char symb2 = s.charAt(i * 2);
            new_str.append(symb1).append(symb2);
        }
        if (s.length() % 2 == 1) new_str.append(s.charAt(s.length() - 1));
        return new_str.toString();
    }
}
