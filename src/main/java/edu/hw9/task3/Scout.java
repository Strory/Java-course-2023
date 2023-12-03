package edu.hw9.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class Scout extends RecursiveTask {
    private final Cell[][] gridMaze;
    private final AtomicBoolean[][] gridVisitedPlaces;
    private final List<Coordinate> currentPath;
    private final Coordinate current;
    private final Coordinate end;


    public Scout(Cell[][] gridMaze, AtomicBoolean[][] gridVisitedPlaces,
        Coordinate current, Coordinate end, List<Coordinate> currentPath) {
        this.gridMaze = gridMaze;
        this.gridVisitedPlaces = gridVisitedPlaces;
        this.current = current;
        this.end = end;
        this.currentPath = currentPath;
    }


    @SuppressWarnings("CyclomaticComplexity")
    @Override
    protected List<Coordinate> compute() {
        gridVisitedPlaces[current.row()][current.col()].set(true);
        List<Coordinate> myPath = myDeepCopy(currentPath);
        myPath.add(current);
        if (current.row() == end.row() && current.col() == end.col()) {
            return myPath;
        }

        List<Scout> scouts = new ArrayList<>();

        int canIGo = 0;
        // Вверх
        if (current.row() != 0
            && !gridMaze[current.row() - 1][current.col()].getWallDown()
            && !gridVisitedPlaces[current.row() - 1][current.col()].get()) {
            Scout scout = new Scout(gridMaze, gridVisitedPlaces,
                new Coordinate(current.row() - 1, current.col()),
                end, myPath);
            scouts.add(scout);
            ++canIGo;
        }
        // Вправо
        if (current.col() != gridMaze[0].length - 1
            && !gridMaze[current.row()][current.col()].getWallRight()
            && !gridVisitedPlaces[current.row()][current.col() + 1].get()) {
            Scout scout = new Scout(gridMaze, gridVisitedPlaces,
                new Coordinate(current.row(), current.col() + 1),
                end, myPath);
            scouts.add(scout);
            ++canIGo;
        }
        // Вниз
        if (current.row() != gridMaze.length - 1
            && !gridMaze[current.row()][current.col()].getWallDown()
            && !gridVisitedPlaces[current.row() + 1][current.col()].get()) {
            Scout scout = new Scout(gridMaze, gridVisitedPlaces,
                new Coordinate(current.row() + 1, current.col()),
                end, myPath);
            scouts.add(scout);
            ++canIGo;
        }
        // Влево
        if (current.col() != 0
            && !gridMaze[current.row()][current.col() - 1].getWallRight()
            && !gridVisitedPlaces[current.row()][current.col() - 1].get()) {
            Scout scout = new Scout(gridMaze, gridVisitedPlaces,
                new Coordinate(current.row(), current.col() - 1),
                end, myPath);
            scouts.add(scout);
            ++canIGo;
        }

        if (canIGo == 0) {
            return null;
        }
        for (Scout scout : scouts) {
            scout.fork();
        }

        for (Scout scout : scouts) {
            if (scout.join() != null) {
                return (List<Coordinate>) scout.join();
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
