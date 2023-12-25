package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {
    }

    public static boolean task6(String string, String subString) {
        String regexSubString = Pattern.quote(subString);
        String regex = ".*" + regexSubString + ".*";
        return string.matches(regex);
    }
}
