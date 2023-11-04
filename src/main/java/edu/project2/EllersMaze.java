package edu.project2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EllersMaze {
    private final int width;
    private final int height;

    private final Cell[][] grid;

    private Map<Integer, Set<Cell>> sets;

    public EllersMaze(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.sets = new HashMap<>();
    }

    public Cell[][] generate() {
        grid[0] = generateFirstLine();
        for (int i = 1; i < height - 1; ++i) {
            grid[i] = generateLine(i - 1);
        }
        grid[height - 1] = generateLastLine(height - 2);
        return grid;
    }

    private Cell[] generateLastLine(int previousLineNumber) {
        Cell[] line = new Cell[width];
        // Создаем множества новой строки (в зависимости от предыдущей),
        // и сразу строим монолит горизонтально стены
        for (int i = 0; i < width; ++i) {
            line[i] = new Cell();
            if ((grid[previousLineNumber][i].getWallDown())) {
                addToMapNewSet(line, i);
            } else {
                addToSet(grid[previousLineNumber][i].getId(), line, i);
            }
            line[i].setWallDown(true);
        }

        // Строим вертикальные стенки и объединяем множества
        for (int i = 0; i < width - 1; ++i) {
            if (line[i].getId() != line[i + 1].getId()) {
                unionOfSets(line[i].getId(), line[i + 1].getId());
            } else {
                line[i].setWallRight(true);
            }
        }
        line[width - 1].setWallRight(true);
        return line;
    }

    private Cell[] generateLine(int previousLineNumber) {
        Cell[] line = new Cell[width];

        // Создаем множества новой строки (в зависимости от предыдущей)
        for (int i = 0; i < width; ++i) {
            line[i] = new Cell();
            if ((grid[previousLineNumber][i].getWallDown())) {
                addToMapNewSet(line, i);
            } else {
                addToSet(grid[previousLineNumber][i].getId(), line, i);
            }
        }

        // Проходим по строке. Строим вертикальные стенки и объединяем множества
        for (int i = 0; i < width - 1; ++i) {
            if (line[i].getId() == line[i + 1].getId() || GenerateNumbers.getRandomBinaryNumber() == 1) {
                line[i].setWallRight(true);
            } else {
                unionOfSets(line[i].getId(), line[i + 1].getId());
            }
        }
        line[width - 1].setWallRight(true);

        // Создаем наборы для строки
        Map<Integer, Set<Cell>> lineSets = new HashMap<>();
        for (int i = 0; i < width; ++i) {
            if (lineSets.containsKey(line[i].getId())) {
                lineSets.get(line[i].getId()).add(line[i]);
            } else {
                Set<Cell> set = new HashSet<>();
                set.add(line[i]);
                lineSets.put(line[i].getId(), set);
            }
        }

        // Строим горизонтальные стенки
        for (Set<Cell> set : lineSets.values()) {
            int count = set.size();
            for (Cell cell : set) {
                if (count > 1 && GenerateNumbers.getRandomBinaryNumber() == 1) {
                    cell.setWallDown(true);
                    --count;
                }
            }
        }
        return line;
    }

    private Cell[] generateFirstLine() {
        Cell[] line = new Cell[width];
//        Arrays.fill(line, new Cell());
        for (int i = 0; i < width; ++i) {
            line[i] = new Cell();
        }

        // Проходим по строке. Ставим стенки, создаем множества
        for (int i = 0; i < width - 1; ++i) {
            if (line[i].getId() == -1) {
                addToMapNewSet(line, i);
            }
            if (GenerateNumbers.getRandomBinaryNumber() == 1) {
                line[i].setWallRight(true);
            } else {
                line[i + 1].setId(line[i].getId());
                addToSet(line[i].getId(), line, i + 1);
            }
        }
        if (line[width - 1].getId() == -1) {
            addToMapNewSet(line, width - 1);
        }
        line[width - 1].setWallRight(true);

        // Создаем горизонтальные стенки
        for (Set<Cell> set : sets.values()) {
            int count = set.size();
            for (Cell cell : set) {
                if (GenerateNumbers.getRandomBinaryNumber() == 1 && count > 1) {
                    cell.setWallDown(true);
                    --count;
                }
            }
        }
        return line;
    }

    private void addToSet(int setId, Cell[] line, int index) {
        line[index].setId(setId);
        sets.get(setId).add(line[index]);
    }

    private void addToMapNewSet(Cell[] line, int index) {
        Set<Cell> set = new HashSet<>();
        set.add(line[index]);
        int id = GenerateNumbers.getId();
        line[index].setId(id);
        sets.put(id, set);
    }

    private void unionOfSets(int setId1, int setId2) {
        for (Cell cell : sets.get(setId2)) {
            cell.setId(setId1);
            sets.get(setId1).add(cell);
        }
        sets.remove(setId2);
    }
}
