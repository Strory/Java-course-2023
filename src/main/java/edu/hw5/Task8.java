package edu.hw5;

import java.util.HashMap;
import java.util.Map;

public class Task8 {
    private Task8() {
    }

    public static Map<String, String> regexes = new HashMap<>() {{
        put("1", "((0|1){2})*[01]");
        put("2", "(0([01]{2})*)|(1([01]{2})*[01])");
        put("3", "(1*01*01*01*)+");
        put("4", "(?!11$)(?!111$)[01]*");
        put("5", "(1)|((1[01])+)|((1[01])+1)");
        put("6", "(?=(.*0){2,})(?!(.*1){2,})[01]*");
        put("7_1", "1?(0+1)*0*");
        put("7_2", "(?!.*1{2,})[01]*"); // 2 варианта решения
    }};

    public static boolean task8(String string, String regexKey) {
        return string.matches(regexes.get(regexKey));
    }

}
