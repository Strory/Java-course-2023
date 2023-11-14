package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class DiskMap implements Map<String, String> {
    private final String fileName;

    public DiskMap(String fileName) {
        this.fileName = fileName;
    }

    private void updateFile(String key, String value) {
        Map<String, String> fileMap = getMap();
        if (fileMap == null) {
            return;
        }
        fileMap.put(key, value);
        try (FileWriter writer = new FileWriter(fileName)) {
            for (var entry : fileMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateFile(Map<String, String> fileMap) {
        if (fileMap == null) {
            return;
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            for (var entry : fileMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("EmptyBlock")
    private void clearFile() {
        try (FileWriter writer = new FileWriter(fileName, false)) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> getMap() {
        File file = new File(fileName);
        if (!(file.exists())) {
            return new HashMap<>();
        }
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            Map<String, String> fileMap = new HashMap<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] mapElement = line.split(":");
                fileMap.put(mapElement[0], mapElement[1]);
            }
            return fileMap;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(getMap()).size();
    }

    @Override
    public boolean isEmpty() {
        return Objects.requireNonNull(getMap()).isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return Objects.requireNonNull(getMap()).containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return Objects.requireNonNull(getMap()).containsKey(value);
    }

    @Override
    public String get(Object key) {
        return Objects.requireNonNull(getMap()).get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        updateFile(key, value);
        return null;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> fileMap = getMap();
        String value = fileMap.remove(key);
        updateFile(fileMap);
        return value;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        Map<String, String> fileMap = getMap();
        for (var entry : m.entrySet()) {
            fileMap.put(entry.getKey(), entry.getValue());
        }
        updateFile(fileMap);
    }

    @Override
    public void clear() {
        clearFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return Objects.requireNonNull(getMap()).keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return Objects.requireNonNull(getMap()).values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return Objects.requireNonNull(getMap()).entrySet();
    }
}
