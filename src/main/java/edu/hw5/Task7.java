package edu.hw5;

import java.util.HashMap;
import java.util.Map;

public class Task7 {
    private Task7() {
    }

    public static Map<String, String> regexes = new HashMap<>() {{
        put("1", "[01]{2}0[01]*");
        put("2", "(0|1)[01]*\\1");
        put("3", "[01]{1,3}");
    }};

    public static boolean tusk7(String string, String regexKey) {
        return string.matches(regexes.get(regexKey));
    }
}
