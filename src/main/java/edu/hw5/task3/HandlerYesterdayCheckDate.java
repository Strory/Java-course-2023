package edu.hw5.task3;

import java.time.LocalDate;

public class HandlerYesterdayCheckDate extends Handler {
    public HandlerYesterdayCheckDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerYesterdayCheckDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        final String KEYWORD = "yesterday";
        if (KEYWORD.equals(request)) {
            return LocalDate.now().minusDays(1);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
