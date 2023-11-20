package edu.project2.pathfinding;

import edu.project2.Cell;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PathfinderBSF {
    private final Cell[][] gridMaze;
    private int[][] distanceGrid;
    private final Coordinate start;
    private final Coordinate end;

    public PathfinderBSF(Cell[][] gridMaze, Coordinate start, Coordinate end) {
        this.gridMaze = gridMaze;
        this.start = new Coordinate(start.col(), start.row());
        this.end = new Coordinate(end.col(), end.row());
    }

    public boolean[][] getGridPath() {
        prepareDistanceGrid();
        bsf();
        return createGridPath(getListPath());
    }

    public List<Coordinate> getCoordinateList() {
        prepareDistanceGrid();
        bsf();
        return getListPath();
    }

    private void prepareDistanceGrid() {
        distanceGrid = new int[gridMaze.length][gridMaze[0].length];
        for (int[] line : distanceGrid) {
            Arrays.fill(line, -1);
        }
    }

    private void bsf() {
        Queue<Coordinate> queue = new ArrayDeque<>();

        int nextDistance = 0;

        Coordinate currentCoordinate = start;
        queue.add(currentCoordinate);
        distanceGrid[currentCoordinate.row()][currentCoordinate.col()] = nextDistance;
        while (!queue.isEmpty()) {
            currentCoordinate = queue.remove();
            if (currentCoordinate.row() == end.row() && currentCoordinate.col() == end.col()) {
                break;
            }
            nextDistance = distanceGrid[currentCoordinate.row()][currentCoordinate.col()] + 1;

            // вверх
            Coordinate up = new Coordinate(currentCoordinate.row() - 1, currentCoordinate.col());
            if (currentCoordinate.row() != 0
                && !gridMaze[up.row()][up.col()].getWallDown()
                && distanceGrid[up.row()][up.col()] == -1) {
                distanceGrid[up.row()][up.col()] = nextDistance;
                queue.add(up);
            }
            // вправо
            Coordinate right = new Coordinate(currentCoordinate.row(), currentCoordinate.col() + 1);
            if (right.col() != distanceGrid[0].length
                && !gridMaze[currentCoordinate.row()][currentCoordinate.col()].getWallRight()
                && distanceGrid[right.row()][right.col()] == -1) {
                distanceGrid[right.row()][right.col()] = nextDistance;
                queue.add(right);
            }
            // вниз
            Coordinate down = new Coordinate(currentCoordinate.row() + 1, currentCoordinate.col());
            if (down.row() != distanceGrid.length
                && !gridMaze[currentCoordinate.row()][currentCoordinate.col()].getWallDown()
                && distanceGrid[down.row()][down.col()] == -1) {
                distanceGrid[down.row()][down.col()] = nextDistance;
                queue.add(down);
            }
            // влево
            Coordinate left = new Coordinate(currentCoordinate.row(), currentCoordinate.col() - 1);
            if (currentCoordinate.col() != 0
                && !gridMaze[left.row()][left.col()].getWallRight()
                && distanceGrid[left.row()][left.col()] == -1) {
                distanceGrid[left.row()][left.col()] = nextDistance;
                queue.add(left);
            }
        }
    }

    private List<Coordinate> getListPath() {
        if (distanceGrid[end.row()][end.col()] == -1) {
            return null;
        }
        List<Coordinate> path = new ArrayList<>();

        Coordinate current = end;
        path.add(current);
        int nextDistance;
        while (!(current.row() == start.row() && current.col() == start.col())) {
            nextDistance = distanceGrid[current.row()][current.col()] - 1;

            if (current.row() != 0
                && distanceGrid[current.row() - 1][current.col()] == nextDistance
                && !gridMaze[current.row() - 1][current.col()].getWallDown()) {
                current = new Coordinate(current.row() - 1, current.col());
            } else if (current.col() != distanceGrid[0].length - 1
                && distanceGrid[current.row()][current.col() + 1] == nextDistance
                && !gridMaze[current.row()][current.col()].getWallRight()) {
                current = new Coordinate(current.row(), current.col() + 1);
            } else if (current.row() != distanceGrid.length - 1
                && distanceGrid[current.row() + 1][current.col()] == nextDistance
                && !gridMaze[current.row()][current.col()].getWallDown()) {
                current = new Coordinate(current.row() + 1, current.col());
            } else if (current.col() != 0 && !gridMaze[current.row()][current.col() - 1].getWallRight()) {
                current = new Coordinate(current.row(), current.col() - 1);
            }
            path.add(current);
        }
        return path;
    }

    public boolean[][] createGridPath(List<Coordinate> path) {
        boolean[][] gridPath = new boolean[distanceGrid.length][distanceGrid[0].length];
        for (boolean[] line : gridPath) {
            Arrays.fill(line, false);
        }
        for (Coordinate coordinate : path) {
            gridPath[coordinate.row()][coordinate.col()] = true;
        }
        return gridPath;
    }
}
