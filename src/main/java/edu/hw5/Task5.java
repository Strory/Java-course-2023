package edu.hw5;

public class Task5 {
    private Task5() {
    }

    public static boolean task5(String string) {
        String regex = "[А-Я]\\d{3}[А-Я]{2}\\d{3}";
        return string.matches(regex);
    }
}
