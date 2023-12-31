package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    private Task2() {}

    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String newName = getCopyName(fileName);
        Path newPath = path.getParent().resolve(newName);
        try {
            Files.copy(path, newPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return;
        }
    }

    public static String getCopyName(String name) {
        String[] splitName = name.split("\\.");
        String nameWithNoType = splitName[0];
        String type = splitName[1];
        if (nameWithNoType.matches(".* - копия$")) {
            return "%s (2).%s".formatted(nameWithNoType, type);
        } else if (nameWithNoType.matches(".* - копия \\([0-9]*\\)$")) {
            Pattern pattern = Pattern.compile(".* - копия \\(([0-9]*)\\)$");
            Matcher matcher = pattern.matcher(nameWithNoType);
            if (matcher.find()) {
                int copyOrder = Integer.parseInt(matcher.group(1));
                String copyStr = " - копия (";
                String oldEnd = "%s%s)".formatted(copyStr, copyOrder);
                String newEnd = "%s%s".formatted(copyStr, copyOrder + 1);
                String newString = nameWithNoType.replace(oldEnd, newEnd);
                return "%s.%s".formatted(newString, type);
            }
        } else {
            return "%s - копия.%s".formatted(nameWithNoType, type);
        }
        return null;
    }
}
