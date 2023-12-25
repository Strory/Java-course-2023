package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HandlerSlashDate2 extends Handler {
    public HandlerSlashDate2(Handler nextHandler) {
        super(nextHandler);
    }

    public HandlerSlashDate2() {
        super(null);
    }

    @Override
    public LocalDate handleRequest(String request) {
        String regex = "^\\d{1,2}/\\d{1,2}/\\d{2}$";
        if (request.matches(regex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
            return LocalDate.parse(request, formatter);
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(request);
        }
        return null;
    }
}
