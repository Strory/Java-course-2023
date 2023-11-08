package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HandlerSlashDate extends Handler {
    public HandlerSlashDate(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerSlashDate() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        String regex = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
        if (request.matches(regex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(request, formatter);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
