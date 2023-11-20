package edu.project2;

import edu.project2.pathfinding.Coordinate;
import edu.project2.pathfinding.PathRender;
import edu.project2.pathfinding.Pathfinder;
import edu.project2.pathfinding.PathfinderBSF;

public class Console {
    private Console() {}

    @SuppressWarnings("MagicNumber")
    public static void paintMazes() {
        // Лабиринт Эллера
        EllersMaze maze1 = new EllersMaze(20, 10);
        Cell[][] gridMaze1 = maze1.generate();
        Render drawer1 = new Render(gridMaze1);
        drawer1.paint();

        // Лабиринт Backtracking
        BacktrackingMaze maze2 = new BacktrackingMaze(20, 10);
        Cell[][] gridMaze2 = maze2.generate();
        Render drawer2 = new Render(gridMaze2);
        drawer2.paint();

        // Поиск пути в глубину
        Coordinate start1 = new Coordinate(0, 0);
        Coordinate end1 = new Coordinate(19, 9);
        Pathfinder pathFinder1 = new Pathfinder(gridMaze2, start1, end1);
        boolean[][] path1 = pathFinder1.getGridPath();

        PathRender drawerPath1 = new PathRender(gridMaze2, path1);
        drawerPath1.paint();

        // Поиск пути в ширину
        Coordinate start2 = new Coordinate(0, 0);
        Coordinate end2 = new Coordinate(19, 9);
        PathfinderBSF pathfinder2 = new PathfinderBSF(gridMaze1, start2, end2);
        boolean[][] path2 = pathfinder2.getGridPath();

        PathRender drawerPath2 = new PathRender(gridMaze1, path2);
        drawerPath2.paint();
    }
}
