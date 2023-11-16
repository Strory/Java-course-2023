package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class LogAnalyzerTest {
    @Test
    @DisplayName("Проверка количества самого частого кода ответа")
    void checkMostResponse() {
        //given
        String url = "https://raw.githubusercontent.com/elastic" +
            "/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        OffsetDateTime date1 = OffsetDateTime.parse("2015-05-17T08:05:00Z");
        OffsetDateTime date2 = OffsetDateTime.parse("2015-05-17T08:05:05Z");
        LogParser parser = new LogParser(url);
        List<LogRecord> logs = parser.getLogs(date1, date2);
        LogAnalyzer analyzer = new LogAnalyzer(logs);

        //when
        int responseCount = analyzer.getMostResponseCods().get(304);

        //then
        assertThat(11).isEqualTo(responseCount);
    }
}
