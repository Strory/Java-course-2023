package edu.project2;

import edu.project2.pathfinding.Coordinate;
import java.util.Arrays;

public class BacktrackingMaze {
    private final int width;
    private final int height;
    private Cell[][] gridMaze;
    private boolean[][] gridVisitedPlaces;

    BacktrackingMaze(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Cell[][] generate() {
        createPrimaryGrid();
        Coordinate start = new Coordinate(GenerateNumbers.randomInt(height), GenerateNumbers.randomInt(width));
        dsf(start);
        return gridMaze;
    }

    @SuppressWarnings({"CyclomaticComplexity", "MagicNumber"})
    private void dsf(Coordinate currentCoordinate) {
        gridVisitedPlaces[currentCoordinate.row()][currentCoordinate.col()] = true;
        int direction = GenerateNumbers.randomFourNumber();

        // хороший рандом доделать позднее

        //вверх
        if (direction == 1 && currentCoordinate.row() != 0
            && !gridVisitedPlaces[currentCoordinate.row() - 1][currentCoordinate.col()]) {
            gridMaze[currentCoordinate.row() - 1][currentCoordinate.col()].setWallDown(false);
            dsf(new Coordinate(currentCoordinate.row() - 1, currentCoordinate.col()));
            // вправо
        } else if (direction == 2 && currentCoordinate.col() != gridMaze[0].length - 1
            && !gridVisitedPlaces[currentCoordinate.row()][currentCoordinate.col() + 1]) {
            gridMaze[currentCoordinate.row()][currentCoordinate.col()].setWallRight(false);
            dsf(new Coordinate(currentCoordinate.row(), currentCoordinate.col() + 1));
            // вниз
        } else if (direction == 3 && currentCoordinate.row() != gridMaze.length - 1
            && !gridVisitedPlaces[currentCoordinate.row() + 1][currentCoordinate.col()]) {
            gridMaze[currentCoordinate.row()][currentCoordinate.col()].setWallDown(false);
            dsf(new Coordinate(currentCoordinate.row() + 1, currentCoordinate.col()));
            // влево
        } else if (direction == 4 && currentCoordinate.col() != 0
            && !gridVisitedPlaces[currentCoordinate.row()][currentCoordinate.col() - 1]) {
            gridMaze[currentCoordinate.row()][currentCoordinate.col() - 1].setWallRight(false);
            dsf(new Coordinate(currentCoordinate.row(), currentCoordinate.col() - 1));
        }

        // Остальные направления
        if (direction != 1 && currentCoordinate.row() != 0
            && !gridVisitedPlaces[currentCoordinate.row() - 1][currentCoordinate.col()]) {
            gridMaze[currentCoordinate.row() - 1][currentCoordinate.col()].setWallDown(false);
            dsf(new Coordinate(currentCoordinate.row() - 1, currentCoordinate.col()));
            // вправо
        }
        if (direction != 2 && currentCoordinate.col() != gridMaze[0].length - 1
            && !gridVisitedPlaces[currentCoordinate.row()][currentCoordinate.col() + 1]) {
            gridMaze[currentCoordinate.row()][currentCoordinate.col()].setWallRight(false);
            dsf(new Coordinate(currentCoordinate.row(), currentCoordinate.col() + 1));
            // вниз
        }
        if (direction != 3 && currentCoordinate.row() != gridMaze.length - 1
            && !gridVisitedPlaces[currentCoordinate.row() + 1][currentCoordinate.col()]) {
            gridMaze[currentCoordinate.row()][currentCoordinate.col()].setWallDown(false);
            dsf(new Coordinate(currentCoordinate.row() + 1, currentCoordinate.col()));
            // влево
        }
        if (direction != 4 && currentCoordinate.col() != 0
            && !gridVisitedPlaces[currentCoordinate.row()][currentCoordinate.col() - 1]) {
            gridMaze[currentCoordinate.row()][currentCoordinate.col() - 1].setWallRight(false);
            dsf(new Coordinate(currentCoordinate.row(), currentCoordinate.col() - 1));
        }
    }

    private void createPrimaryGrid() {
        gridMaze = new Cell[height][width];
        gridVisitedPlaces = new boolean[height][width];
        for (boolean[] place : gridVisitedPlaces) {
            Arrays.fill(place, false);
        }
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                gridMaze[i][j] = new Cell(true, true);
            }
        }
    }
}
