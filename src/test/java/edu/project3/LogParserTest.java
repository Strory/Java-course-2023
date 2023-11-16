package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

public class LogParserTest {
    @Test
    @DisplayName("Проверка существования единственной строчки")
    void checkOneLine() {
        //given
        String url = "https://raw.githubusercontent.com/elastic" +
            "/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        OffsetDateTime date1 = OffsetDateTime.parse("2015-05-17T08:05:00Z");
        OffsetDateTime date2 = OffsetDateTime.parse("2015-05-17T08:05:00Z");
        LogParser parser = new LogParser(url);

        int size = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        String date = "17/мая/2015:08:05:00 +0000";

        //when
        List<LogRecord> logs = parser.getLogs(date1, date2);

        //then
        assertThat(size).isEqualTo(logs.size());
    }

    @Test
    @DisplayName("Проверка конкретного лога по адресу")
    void checkDataByRange() {
        //given
        String url = "https://raw.githubusercontent.com/elastic" +
            "/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        OffsetDateTime date1 = OffsetDateTime.parse("2015-05-17T08:05:00Z");
        OffsetDateTime date2 = OffsetDateTime.parse("2015-05-20T08:05:00Z");
        LogParser parser = new LogParser(url);

        String ip = "80.91.33.133";

        //expect
        List<LogRecord> logs = parser.getLogs(date1, date2);
        for (LogRecord log : logs) {
            if (ip.equals(log.address())) {
                return;
            }
        }
        Assertions.fail();
    }
}
