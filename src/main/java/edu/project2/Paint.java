package edu.project2;

import edu.project2.pathfinding.Coordinate;
import edu.project2.pathfinding.PathRender;
import edu.project2.pathfinding.Pathfinder;

public class Paint {
    private Paint() {
    }

    // Класс для создания и отрисовки лабиринта

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        paintPathFinding();
    }

    public static void paintPathFinding() {
        EllersMaze maze = new EllersMaze(PaintSettings.MAZE_WIDTH, PaintSettings.MAZE_HEIGHT);
        Cell[][] gridMaze = maze.generate();

        // Отрисовка просто лабиринта
//        Render drawer = new Render(gridMaze, PaintSettings.cellWidthSize, PaintSettings.cellHeightSize,
//            PaintSettings.wallSymbol, PaintSettings.emptySymbol);
//        drawer.paint();

        // Отрисовка поиска пути
        Coordinate start = new Coordinate(PaintSettings.COORDINATE_START_X, PaintSettings.COORDINATE_START_Y);
        Coordinate end = new Coordinate(PaintSettings.COORDINATE_END_X, PaintSettings.COORDINATE_ENX_Y);
        Pathfinder pathFinder = new Pathfinder(gridMaze, start, end);
        boolean[][] path = pathFinder.getGridPath();

        PathRender drawerPath = new PathRender(gridMaze, PaintSettings.CELL_WIDTH_SIZE, PaintSettings.CELL_HEIGHT_SIZE,
            PaintSettings.WALL_SYMBOL, PaintSettings.EMPTY_SYMBOL, path, PaintSettings.POINT_SYMBOL
        );
        drawerPath.paint();

        // Отрисовка посещенных мест
//        boolean[][] places = pathFinder.getGridVisitedPlaces();
//        PathRender drawerPlaces = new PathRender(gridMaze, PaintSettings.cellWidthSize, PaintSettings.cellHeightSize,
//            PaintSettings.wallSymbol, PaintSettings.emptySymbol, places, PaintSettings.pointSymbol);
//        drawerPlaces.paint();
    }
}
