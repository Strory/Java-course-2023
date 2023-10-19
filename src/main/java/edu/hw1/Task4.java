package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String s) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < s.length() / 2; ++i) {
            char symb1 = s.charAt(i * 2 + 1);
            char symb2 = s.charAt(i * 2);
            newStr.append(symb1).append(symb2);
        }
        if (s.length() % 2 == 1) {
            newStr.append(s.charAt(s.length() - 1));
        }
        return newStr.toString();
    }
}
