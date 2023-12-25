package edu.project3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LogAnalyzer {
    private final List<LogRecord> logList;

    LogAnalyzer(List<LogRecord> logs) {
        logList = logs;
    }

    public int requestsCount() {
        return logList.size();
    }

    public Map<String, Integer> getMostRequestedResources() {
        Map<String, Integer> requestedResources = new HashMap<>();

        for (LogRecord log : logList) {
            String resource = log.resource();
            if (requestedResources.containsKey(resource)) {
                int count = requestedResources.get(resource);
                ++count;
                requestedResources.put(resource, count);
            } else {
                requestedResources.put(resource, 1);
            }
        }
        Map<String, Integer> sortedMap = new TreeMap<>(
            (o1, o2) -> requestedResources.get(o2).compareTo(requestedResources.get(o1)));
        sortedMap.putAll(requestedResources);
        return sortedMap;
    }

    public Map<Integer, Integer> getMostResponseCods() {
        Map<Integer, Integer> cods = new HashMap<>();
        for (LogRecord log : logList) {
            Integer code = log.code();
            if (cods.containsKey(code)) {
                int count = cods.get(code);
                ++count;
                cods.put(code, count);
            } else {
                cods.put(code, 1);
            }
        }
        Map<Integer, Integer> sortedMap = new TreeMap<>(
            (o1, o2) -> cods.get(o2).compareTo(cods.get(o1)));
        sortedMap.putAll(cods);
        return sortedMap;
    }

    public long getAverageSize() {
        long sum = 0;
        for (LogRecord log : logList) {
            sum += log.size();
        }
        return sum / logList.size();
    }

    public Map<Integer, Integer> getMostVisitedHours() {
        Map<Integer, Integer> hours = new HashMap<>();
        for (LogRecord log : logList) {
            int hour = log.date().getHour();

            if (hours.containsKey(hour)) {
                int count = hours.get(hour);
                ++count;
                hours.put(hour, count);
            } else {
                hours.put(hour, 1);
            }
        }
        Map<Integer, Integer> sortedMap = new TreeMap<>(
            (o1, o2) -> hours.get(o2).compareTo(hours.get(o1)));
        sortedMap.putAll(hours);
        return sortedMap;
    }

    public String getMostCommonAddress() {
        Map<String, Integer> addresses = new HashMap<>();
        for (LogRecord log : logList) {
            String address = log.address();

            if (addresses.containsKey(address)) {
                int count = addresses.get(address);
                ++count;
                addresses.put(address, count);
            } else {
                addresses.put(address, 1);
            }
        }
        String mostVisitedAddress = logList.get(0).address();
        for (var entry : addresses.entrySet()) {
            if (entry.getValue() > addresses.get(mostVisitedAddress)) {
                mostVisitedAddress = entry.getKey();
            }
        }
        return mostVisitedAddress;
    }
}
