package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        Handler myHandler1 = new HandlerDashDate();
        Handler myHandler2 = new HandlerSlashDate(myHandler1);
        Handler myHandler3 = new HandlerSlashDate2(myHandler2);
        Handler myHandler4 = new HandlerTomorrowCheckDate(myHandler3);
        Handler myHandler5 = new HandlerTodayCheckDate(myHandler4);
        Handler myHandler6 = new HandlerYesterdayCheckDate(myHandler5);
        Handler myHandler7 = new HandlerOneDayAgoCheckDate(myHandler6);
        Handler myHandler8 = new HandlerAnyDaysAgoCheckDate(myHandler7);

        LocalDate date = myHandler8.handleRequest(string);
        return Optional.ofNullable(date);
    }
}
