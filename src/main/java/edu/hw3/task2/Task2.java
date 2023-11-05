package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {}

    public static List<String> splitBrackets(String brackets) {
        List<String> arrBrackets = new ArrayList<>();
        int openBracketCounter = 0;
        int startIndex = 0;
        for (int i = 0; i < brackets.length(); ++i) {
            char bracket = brackets.charAt(i);
            if (bracket == '(') {
                ++openBracketCounter;
            } else {
                --openBracketCounter;
            }
            if (openBracketCounter == 0) {
                arrBrackets.add(brackets.substring(startIndex, i + 1));
                startIndex = i + 1;
            }
        }
        return arrBrackets;
    }
}
