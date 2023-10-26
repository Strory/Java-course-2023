package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;

public class Task3 {
    private Task3() {}

    public static <T> HashMap<T, Integer> makeFrequencyMap(List<T> list) {
        HashMap<T, Integer> frequencyMap = new HashMap<>();
        for (T elment : list) {
            if (frequencyMap.containsKey(elment)) {
                int quantity = frequencyMap.get(elment);
                ++quantity;
                frequencyMap.put(elment, quantity);
            } else {
                frequencyMap.put(elment, 1);
            }
        }
        return frequencyMap;
    }
}
