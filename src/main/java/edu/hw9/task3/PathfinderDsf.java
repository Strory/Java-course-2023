package edu.hw9.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;

public class PathfinderDsf {
    private final Cell[][] gridMaze;
    private AtomicBoolean[][] gridVisitedPlaces;
    private final Coordinate start;
    private final Coordinate end;

    public PathfinderDsf(Cell[][] gridMaze, Coordinate start, Coordinate end) {
        this.gridMaze = gridMaze;
        this.start = new Coordinate(start.col(), start.row());
        this.end = new Coordinate(end.col(), end.row());
        this.gridVisitedPlaces = new AtomicBoolean[gridMaze.length][gridMaze[0].length];
        for (int i = 0; i < gridVisitedPlaces.length; ++i) {
            for (int j = 0; j < gridVisitedPlaces[0].length; ++j) {
                gridVisitedPlaces[i][j] = new AtomicBoolean();
            }
        }
    }

    public boolean[][] getGridPath() {
        List<Coordinate> path = getPath();
        boolean[][] gridPath = new boolean[gridMaze.length][gridMaze[0].length];
        for (boolean[] line : gridPath) {
            Arrays.fill(line, false);
        }
        for (Coordinate coordinate : path) {
            gridPath[coordinate.row()][coordinate.col()] = true;
        }
        return gridPath;
    }

    public List<Coordinate> getPath() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Scout scout = new Scout(gridMaze, gridVisitedPlaces, start, end, new ArrayList<>());
        List<Coordinate> path = (List<Coordinate>) forkJoinPool.invoke(scout);
        return path;
    }
}
