package edu.hw5.task3;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerAnyDaysAgoCheckDate extends Handler {
    public HandlerAnyDaysAgoCheckDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerAnyDaysAgoCheckDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        String regex = "^(\\d+) days ago$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(request);
        if (matcher.find()) {
            int daysAgo = Integer.parseInt(matcher.group(1));
            return LocalDate.now().minusDays(daysAgo);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
