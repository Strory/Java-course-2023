package edu.hw9.task3;

public class Render {
    private final int cellWidth;
    private final int cellHeight;
    private final char wallSymbol;
    private final char emptySymbol;
    private String[][][] grid;
    private final Cell[][] mazeGrid;

    @SuppressWarnings("MagicNumber")
    public Render(Cell[][] maze) {
        cellWidth = 4;
        cellHeight = 2;
        wallSymbol = '█';
        emptySymbol = ' ';
        mazeGrid = maze;
    }

    public Render(Cell[][] maze, int width, int height, char wall, char empty) {
        cellWidth = width;
        cellHeight = height;
        wallSymbol = wall;
        emptySymbol = empty;
        mazeGrid = maze;
    }

    private String generateCellLine(Cell cell) {
        return String.valueOf(emptySymbol).repeat(cellWidth - 1)
            + (cell.getWallRight() ? wallSymbol : emptySymbol);
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
            for (int j = 0; j < cellHeight - 1; ++j) {
                line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
            }
            line[cellHeight - 1][i] = generateCellDownLine(mazeGrid[colIndex][i], colIndex + 1, i);
        }
        return line;
    }

    private String[][] generateLastLine(int colIndex) {
        String[][] line = new String[cellHeight][mazeGrid[colIndex].length];
        for (int i = 0; i < mazeGrid[colIndex].length; ++i) {
            for (int j = 0; j < cellHeight - 1; ++j) {
                line[j][i] = generateCellLine(mazeGrid[colIndex][i]);
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
                    System.out.print(grid[i][j][k]);
                }
                System.out.println();
            }
        }
    }
}
