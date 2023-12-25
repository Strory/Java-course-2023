package edu.hw5;

public class Task4 {
    private Task4() {
    }

    public static boolean task4(String string) {
        String regex = ".*[~!@#$%^&*|].*";
        return string.matches(regex);
    }
}
