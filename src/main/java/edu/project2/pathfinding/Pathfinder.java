package edu.project2.pathfinding;

import edu.project2.Cell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pathfinder {
    private final Cell[][] gridMaze;
    private boolean[][] gridVisitedPlaces;
    private final Coordinate start;
    private final Coordinate end;

    public Pathfinder(Cell[][] gridMaze) {
        this.gridMaze = gridMaze;
        start = new Coordinate(0, 0);
        end = new Coordinate(gridMaze[0].length, gridMaze.length);
    }

    public Pathfinder(Cell[][] gridMaze, Coordinate start, Coordinate end) {
        this.gridMaze = gridMaze;
        this.start = start;
        this.end = end;
    }

    public boolean[][] getGridPath() {
        gridVisitedPlaces = new boolean[gridMaze.length][gridMaze[0].length];
        List<Coordinate> coordinates = dsf(null, VisitedVertex.STAY, new ArrayList<>());
        boolean[][] gridPath = new boolean[gridMaze.length][gridMaze[0].length];
        for (boolean[] line : gridPath) {
            Arrays.fill(line, false);
        }
        for (Coordinate coordinate : coordinates) {
            gridPath[coordinate.col()][coordinate.row()] = true;
        }
        return gridPath;
    }

    public boolean[][] getGridVisitedPlaces() {
        return gridVisitedPlaces;
    }

    // Для теста
    public List<Coordinate> getCoordinateList() {
        gridVisitedPlaces = new boolean[gridMaze.length][gridMaze[0].length];
        return dsf(null, VisitedVertex.STAY, new ArrayList<>());
    }

    @SuppressWarnings({"CyclomaticComplexity", "ReturnCount"})
    private List<Coordinate> dsf(
        Vertex previousVertex,
        VisitedVertex entryDirection,
        List<Coordinate> previousMilkyWay
    ) {
        Coordinate currentCoordinate = switch (entryDirection) {
            case UP -> new Coordinate(
                previousVertex.coordinate().row(),
                previousVertex.coordinate().col() + 1
            );
            case DOWN -> new Coordinate(
                previousVertex.coordinate().row(),
                previousVertex.coordinate().col() - 1
            );
            case LEFT -> new Coordinate(
                previousVertex.coordinate().row() + 1,
                previousVertex.coordinate().col()
            );
            case RIGHT -> new Coordinate(
                previousVertex.coordinate().row() - 1,
                previousVertex.coordinate().col()
            );
            default -> new Coordinate(
                start.row(),
                start.col()
            );
        };
        Vertex vertex = new Vertex(currentCoordinate);
        gridVisitedPlaces[currentCoordinate.col()][currentCoordinate.row()] = true;
        List<Coordinate> milkyWay = myDeepCopy(previousMilkyWay);
        milkyWay.add(currentCoordinate);

        // проверка на прибытие в точку назначения
        if (currentCoordinate.col() == end.col() && currentCoordinate.row() == end.row()) {
            return milkyWay;
        }

        // переход в другие ячейки

        // наверх
        if (currentCoordinate.col() != 0
            && !gridMaze[currentCoordinate.col() - 1][currentCoordinate.row()].getWallDown()
            && !gridVisitedPlaces[currentCoordinate.col() - 1][currentCoordinate.row()]) {
            List<Coordinate> newMilkyWay = dsf(vertex, VisitedVertex.DOWN, milkyWay);
            if (newMilkyWay != null) {
                return newMilkyWay;
            }
            // вправо
        }
        if (currentCoordinate.row() != gridMaze[0].length - 1
            && !gridMaze[currentCoordinate.col()][currentCoordinate.row()].getWallRight()
            && !gridVisitedPlaces[currentCoordinate.col()][currentCoordinate.row() + 1]) {
            List<Coordinate> newMilkyWay = dsf(vertex, VisitedVertex.LEFT, milkyWay);
            if (newMilkyWay != null) {
                return newMilkyWay;
            }
            // вниз
        }
        if (currentCoordinate.col() != gridMaze.length - 1
            && !gridMaze[currentCoordinate.col()][currentCoordinate.row()].getWallDown()
            && !gridVisitedPlaces[currentCoordinate.col() + 1][currentCoordinate.row()]) {
            List<Coordinate> newMilkyWay = dsf(vertex, VisitedVertex.UP, milkyWay);
            if (newMilkyWay != null) {
                return newMilkyWay;
            }
            // влево
        }
        if (currentCoordinate.row() != 0
            && !gridMaze[currentCoordinate.col()][currentCoordinate.row() - 1].getWallRight()
            && !gridVisitedPlaces[currentCoordinate.col()][currentCoordinate.row() - 1]) {
            List<Coordinate> newMilkyWay = dsf(vertex, VisitedVertex.RIGHT, milkyWay);
            if (newMilkyWay != null) {
                return newMilkyWay;
            }
        }
        return null;
    }

    private List<Coordinate> myDeepCopy(List<Coordinate> inputList) {
        List<Coordinate> newList = new ArrayList<>();
        for (Coordinate coordinate : inputList) {
            Coordinate newC = new Coordinate(coordinate.row(), coordinate.col());
            newList.add(newC);
        }
        return newList;
    }
}

