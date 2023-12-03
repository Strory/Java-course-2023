package edu.hw9.task3;

public class Console {
    private Console() {}

    @SuppressWarnings("MagicNumber")
    public static void showPath() {
        // Лабиринт Backtracking
        BacktrackingMaze maze2 = new BacktrackingMaze(30, 12);
        Cell[][] gridMaze2 = maze2.generate();
        Render drawer2 = new Render(gridMaze2);
        drawer2.paint();

        // Поиск пути в глубину
        Coordinate start1 = new Coordinate(0, 0);
        Coordinate end1 = new Coordinate(29, 11);
        PathfinderDsf pathFinder1 = new PathfinderDsf(gridMaze2, start1, end1);
        boolean[][] path1 = pathFinder1.getGridPath();

        PathRender drawerPath1 = new PathRender(gridMaze2, path1);
        drawerPath1.paint();
    }
}
