package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;

public class Console {
    public static void inputConsole(String url, String from, String to, FileType type) {
        OffsetDateTime date1 = OffsetDateTime.parse(from);
        OffsetDateTime date2 = OffsetDateTime.parse(to);

        LogParser parser = new LogParser(url);
        List<LogRecord> logs = parser.getLogs(date1, date2);

        LogAnalyzer analyzer = new LogAnalyzer(logs);

        ReportPainter painter = new ReportPainter(url, date1, date2, analyzer.requestsCount(),
            analyzer.getAverageSize(), analyzer.getMostRequestedResources(), analyzer.getMostResponseCods(),
            analyzer.getMostVisitedHours(), analyzer.getMostCommonAddress(), type);

        painter.fileWriter();
    }

    // перегрузка для расширения файла по умолчанию
    public static void inputConsole(String url, String from, String to) {
        FileType fileType = FileType.MD;
        OffsetDateTime date1 = OffsetDateTime.parse(from);
        OffsetDateTime date2 = OffsetDateTime.parse(to);

        LogParser parser = new LogParser(url);
        List<LogRecord> logs = parser.getLogs(date1, date2);

        LogAnalyzer analyzer = new LogAnalyzer(logs);

        ReportPainter painter = new ReportPainter(url, date1, date2, analyzer.requestsCount(),
            analyzer.getAverageSize(), analyzer.getMostRequestedResources(), analyzer.getMostResponseCods(),
            analyzer.getMostVisitedHours(), analyzer.getMostCommonAddress(), fileType);

        painter.fileWriter();
    }

    // перегрузка без конечной даты
    public static void inputConsole(String url, String from) {
        FileType type = FileType.MD;
        OffsetDateTime date1 = OffsetDateTime.parse(from);

        LogParser parser = new LogParser(url);
        List<LogRecord> logs = parser.getLogs(date1);

        LogAnalyzer analyzer = new LogAnalyzer(logs);

        ReportPainter painter = new ReportPainter(url, date1, null, analyzer.requestsCount(),
            analyzer.getAverageSize(), analyzer.getMostRequestedResources(), analyzer.getMostResponseCods(),
            analyzer.getMostVisitedHours(), analyzer.getMostCommonAddress(), type);

        painter.fileWriter();
    }

    // перегрузка без конечной даты, но с расширением
    public static void inputConsole(String url, String from, FileType type) {
        OffsetDateTime date1 = OffsetDateTime.parse(from);

        LogParser parser = new LogParser(url);
        List<LogRecord> logs = parser.getLogs(date1);

        LogAnalyzer analyzer = new LogAnalyzer(logs);

        ReportPainter painter = new ReportPainter(url, date1, null, analyzer.requestsCount(),
            analyzer.getAverageSize(), analyzer.getMostRequestedResources(), analyzer.getMostResponseCods(),
            analyzer.getMostVisitedHours(), analyzer.getMostCommonAddress(), type);

        painter.fileWriter();
    }
}
