package edu.project3;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportPainter {
    private Map<Integer, String> responseCodes = new HashMap<>(Map.of(
        200, "OK",
        206, "Partial Content",
        304, "NotModified",
        403, "Forbidden",
        404, "Not Found",
        416, "Range Not Satisfiable",
        500, "Internal Server Error"
    ));
    private final String URL;
    private final OffsetDateTime startDate;
    private final OffsetDateTime finalDate;
    private final int requestCount;
    private final long averageSize;
    private final Map<String, Integer> resources;
    private final Map<Integer, Integer> codes;
    private final Map<Integer, Integer> mostVisitedHour;
    private final String mostCommonAddress;
    private final FileType type;

    ReportPainter(String URL, OffsetDateTime startDate, OffsetDateTime finalDate, int requestCount, long averageSize,
        Map<String, Integer> resources, Map<Integer, Integer> codes, Map<Integer, Integer> mostVisitedHour,
        String mostCommonAddress, FileType type) {
        this.URL = URL;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.requestCount = requestCount;
        this.averageSize = averageSize;
        this.resources = resources;
        this.codes = codes;
        this.mostVisitedHour = mostVisitedHour;
        this.mostCommonAddress = mostCommonAddress;
        this.type = type;
    }

    public void fileWriter() {
        String divider = "|:" + "-".repeat(21) + ":|" + "-".repeat(16) + ":|";
        String dividerCode = "|:" + "-".repeat(3) + ":|:" + "-".repeat(21)
            + ":|" + "-".repeat(11) + ":|";
        String format = "| %-21s | %-15s |%n";
        String formatCode = "| %-3s | %-21s | %,-10d |%n";
        String formatCodeHead = "| %-3s | %-21s | %-10s |%n";
        String url = getUrlString(URL);
        String fileType = switch (type) {
            case FileType.MD -> "md";
            case FileType.ADOC -> "adoc";
        };
        String finaleDate = (this.finalDate == null) ? "-" : getDate(this.finalDate);

        try (PrintWriter writer = new PrintWriter("report.%s".formatted(fileType))) {
            writer.println("#### Общая информация\n");
            writer.printf(format, "Метрика", "Значение");
            writer.println(divider);
            writer.printf(format, "Файл/ссылка", url);
            writer.printf(format, "Начальная дата", getDate(startDate));
            writer.printf(format, "Конечная дата", finaleDate);
            writer.printf(format, "Количество запросов", requestCount);
            writer.printf(format, "Средний размер ответа", averageSize + "b");
            writer.printf(format, "Самый частый адрес", mostCommonAddress);

            writer.println("\n#### Запрашиваемые ресурсы\n");
            writer.printf(format, "Ресурс", "Количество");
            writer.println(divider);
            int iter = 0;
            for (Map.Entry entry : resources.entrySet()) {
                writer.printf(format, entry.getKey(), entry.getValue());
                if (iter == 2) {
                    break;
                }
                ++iter;
            }
            writer.println("\n#### Коды ответа\n");
            writer.printf(formatCodeHead, "Код", "Имя", "Количество");
            writer.println(dividerCode);
            iter = 0;
            for (var entry : codes.entrySet()) {
                writer.printf(formatCode, entry.getKey(), responseCodes.get(entry.getKey()), entry.getValue());
                if (iter == 2) {
                    break;
                }
                ++iter;
            }

            writer.println("\n#### Более посещаемый час\n");
            writer.printf(format, "Час", "Количество");
            writer.println(divider);
            iter = 0;
            for (var entry : mostVisitedHour.entrySet()) {
                writer.printf(format, getHourString(entry.getKey()), entry.getValue());
                if (iter == 2) {
                    break;
                }
                ++iter;
            }
        } catch (IOException e) {
            return;
        }
    }

    private String getHourString(int hour) {
        String hourString;
        if (hour != 23) {
            if (hour < 10) {
                return  "0%d:00 - 0%d:00".formatted(hour, hour + 1);
            }
            return  "%d:00 - %d:00".formatted(hour, hour + 1);
        } else {
            return  "%d:00 - %d0:00".formatted(hour, 0);
        }
    }

    private String getUrlString(String url) {
        String regex = "https://(.{15}).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()){
            return matcher.group(1);
        } else {
            regex = ".*/(.*\\..*)";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(url);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    private String getDate(OffsetDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String newDate = date.format(formatter);
        return newDate;
    }
}
