package edu.project2.pathfinding;

import edu.project2.Cell;

public class PathRender {
    private final int cellWidth;
    private final int cellHeight;
    private final char wallSymbol;
    private final char emptySymbol;
    private final char pointSymbol;
    private String[][][] grid;
    private final Cell[][] mazeGrid;
    private final boolean[][] wayGrid;

    @SuppressWarnings("MagicNumber")
    public PathRender(Cell[][] maze, boolean[][] wayGrid) {
        cellWidth = 4;
        cellHeight = 2;
        wallSymbol = '█';
        emptySymbol = ' ';
        pointSymbol = '?';
        mazeGrid = maze;
        this.wayGrid = wayGrid;
    }

    public PathRender(Cell[][] maze, int width, int height, char wall, char empty, boolean[][] wayGrid, char point) {
        cellWidth = width;
        cellHeight = height;
        wallSymbol = wall;
        emptySymbol = empty;
        mazeGrid = maze;
        this.wayGrid = wayGrid;
        pointSymbol = point;
    }

    private String generateCellLine(Cell cell) {
        return String.valueOf(emptySymbol).repeat(cellWidth - 1)
            + (cell.getWallRight() ? wallSymbol : emptySymbol);
    }

    private String generateCellLineWithPoint(Cell cell) {
        return
            String.valueOf(emptySymbol).repeat((cellWidth - 1) / 2) + pointSymbol
                + String.valueOf(emptySymbol).repeat((cellWidth % 2 == 0) ? ((cellWidth - 1) / 2)
                : ((cellWidth - 1) / 2) - 1) + (cell.getWallRight() ? wallSymbol : emptySymbol);
    }

    private String generateCellDownLine(Cell cell, int cellDownColCoordinate, int cellDownRowCoordinate) {
        return String.valueOf(cell.getWallDown() ? wallSymbol : emptySymbol).repeat(cellWidth - 1)
            + ((cell.getWallRight() || cell.getWallDown()
            || mazeGrid[cellDownColCoordinate][cellDownRowCoordinate].getWallRight())
            ? wallSymbol : emptySymbol);
    }

    private String generateCellDownLineForLastLine() {
        return String.valueOf(wallSymbol).repeat(cellWidth);
    }

    private String[][] generateLine(int colIndex) {
        String[][] line = new String[cellHeight][mazeGrid[colIndex].length];
        for (int i = 0; i < mazeGrid[colIndex].length; ++i) {
            if (wayGrid[colIndex][i]) {
                int firstHeight = (cellHeight % 2 == 0) ? (cellHeight - 1) / 2 : (cellHeight - 2) / 2;
                for (int j = 0; j < firstHeight; ++j) {
                    line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
                }
                line[firstHeight][i] = generateCellLineWithPoint(mazeGrid[colIndex][i]);
                for (int j = firstHeight + 1; j < cellHeight - firstHeight; ++j) {
                    line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
                }
            } else {
                for (int j = 0; j < cellHeight - 1; ++j) {
                    line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
                }
            }
            line[cellHeight - 1][i] = generateCellDownLine(mazeGrid[colIndex][i], colIndex + 1, i);
        }
        return line;
    }

    private String[][] generateLastLine(int colIndex) {
        String[][] line = new String[cellHeight][mazeGrid[colIndex].length];
        for (int i = 0; i < mazeGrid[colIndex].length; ++i) {
            if (wayGrid[colIndex][i]) {
                int firstHeight = (cellHeight % 2 == 0) ? (cellHeight - 1) / 2 : (cellHeight - 2) / 2;
                for (int j = 0; j < firstHeight; ++j) {
                    line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
                }
                line[firstHeight][i] = generateCellLineWithPoint(mazeGrid[colIndex][i]);
                for (int j = firstHeight + 1; j < cellHeight - firstHeight; ++j) {
                    line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
                }
            } else {
                for (int j = 0; j < cellHeight - 1; ++j) {
                    line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
                }
            }
            line[cellHeight - 1][i] = generateCellDownLineForLastLine();
        }
        return line;
    }

    private void generateGrid() {
        grid = new String[mazeGrid.length][mazeGrid[0].length][cellHeight];
        for (int i = 0; i < grid.length - 1; ++i) {
            grid[i] = generateLine(i);
        }
        grid[mazeGrid.length - 1] = generateLastLine(mazeGrid.length - 1);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void paint() {
        generateGrid();
        String firstWall = String.valueOf(wallSymbol).repeat(mazeGrid[0].length * cellWidth + 1);
        System.out.println(firstWall);
        for (int i = 0; i < mazeGrid.length; ++i) {
            for (int j = 0; j < cellHeight; ++j) {
                System.out.print(wallSymbol);
                for (int k = 0; k < mazeGrid[0].length; ++k) {
                    // Красный путь
                    if (grid[i][j][k].contains(String.valueOf(pointSymbol))) {
                        String redColor = "\u001B[31m";
                        String resetColor = "\u001B[0m";
                        System.out.print(redColor + grid[i][j][k].substring(0, grid[i][j][k].length() - 1) + resetColor
                            + grid[i][j][k].charAt(grid[i][j][k].length() - 1));
                    } else {
                        System.out.print(grid[i][j][k]);
                    }
                }
                System.out.println();
            }
        }
    }
}
