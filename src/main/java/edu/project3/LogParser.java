package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private final String URL;
    private List<LogRecord> logList;

    LogParser(String url) {
        this.URL = url;
        createLogList();
    }

    public List<LogRecord> getLogs(OffsetDateTime from, OffsetDateTime to) {
        int bottomIndex = binarySearchDateBottomLimit(logList, from);
        int topIndex = binarySearchDateTopLimit(logList, to);
        List<LogRecord> logSlice = new ArrayList<>();
        for (int i = bottomIndex; i <= topIndex; ++i) {
            logSlice.add(logList.get(i));
        }
        return logSlice;
    }

    public List<LogRecord> getLogs(OffsetDateTime from) {
        int bottomIndex = binarySearchDateBottomLimit(logList, from);
        List<LogRecord> logSlice = new ArrayList<>();
        for (int i = bottomIndex; i < logList.size(); ++i) {
            logSlice.add(logList.get(i));
        }
        return logSlice;
    }

    public List<LogRecord> getLogs() {
        return logList;
    }

    private void createLogList() {
        String[] logStrings = (URL.matches("https?://.*")) ? getStrings() : getStringsFromFile();
        logList = getLogList(logStrings);
        quickSort(logList, 0, logList.size() - 1);
    }

    // Быстрая сортировка
    private void quickSort(List<LogRecord> logs, int low, int high) {
        if (low < high) {
            int p = partition(logs, low, high);
            quickSort(logs, low, p - 1);
            quickSort(logs, p + 1, high);
        }
    }

    private int partition(List<LogRecord> logs, int low, int high) {
        LogRecord pivot = logs.get(high);
        int i = low - 1;
        for (int j = low; j < high; ++j) {
            if (logs.get(j).date().isBefore(pivot.date())) {
                ++i;
                LogRecord tmp = logs.get(j);
                logs.set(j, logs.get(i));
                logs.set(i, tmp);
            }
        }
        LogRecord tmp = logs.get(i + 1);
        logs.set(i + 1, logs.get(high));
        logs.set(high, tmp);
        return i + 1;
    }

    private int binarySearchDateBottomLimit(List<LogRecord> logs, OffsetDateTime date) {
        // ищем нижнюю границу

        int l = 0;
        int r = logs.size();
        int middle = 0;

        while (r - l > 1) {
            middle = (r + l) / 2;
            if (logs.get(middle).date().isBefore(date)) {
                l = middle;
            } else {
                r = middle;
            }
        }
        if (logs.get(l).date().isEqual(date)){
            return l;
        }
        return r;
    }

    private int binarySearchDateTopLimit(List<LogRecord> logs, OffsetDateTime date) {
        // ищем верхнюю границу

        int l = 0;
        int r = logs.size() - 1;
        int middle = 0;

        while (r - l > 1) {
            middle = (r + l) / 2;
            if (logs.get(middle).date().isAfter(date)) {
                r = middle;
            } else {
                l = middle;
            }
        }
        if (logs.get(r).date().isEqual(date)){
            return r;
        }
        return l;
    }

    private String[] getStrings() {
        int codeSuccessful = 200;
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .GET()
                .build();
            var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != codeSuccessful) {
                return null;
            }
            String body = response.body();
            return body.split("\n");
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return null;
        }
    }

    private String[] getStringsFromFile() {
        String fileName = URL;
        Path path = Paths.get(fileName);

        try {
            String data = Files.readString(path, StandardCharsets.UTF_8);
            return data.split("\n");
        } catch (IOException e) {
            return null;
        }
    }

    private List<LogRecord> getLogList(String[] loggString) {
        String regex = "(.*) - - \\[(.*)] \"(.*) (.*) (.*)\" (.*) (.*) \"-\" \"(.*)\"";
        Pattern pattern = Pattern.compile(regex);

        logList = new ArrayList<>();
        for (String strLog : loggString) {
            Matcher matcher = pattern.matcher(strLog);
            if (matcher.find()) {
                logList.add(new LogRecord(matcher.group(1), getDate(matcher.group(2)),
                    matcher.group(3), matcher.group(4), matcher.group(5),
                    Integer.parseInt(matcher.group(6)), Long.parseLong(matcher.group(7)), matcher.group(8)));
            }
        }
        return logList;
    }

    private OffsetDateTime getDate(String strData) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd/MMM/yyyy:HH:mm:ss Z");

        DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);
        return OffsetDateTime.parse(strData, formatter);
    }
}
