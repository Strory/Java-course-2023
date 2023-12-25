package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HandlerDashDate extends Handler {
    public HandlerDashDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerDashDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        String regex = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
        if (request.matches(regex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            return LocalDate.parse(request, formatter);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
