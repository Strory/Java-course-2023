package edu.hw9;

import edu.hw9.task2.Crawler;
import edu.hw9.task2.FileUnit;
import edu.hw9.task2.MyTree;
import edu.hw9.task3.BacktrackingMaze;
import edu.hw9.task3.Cell;
import edu.hw9.task3.Coordinate;
import edu.hw9.task3.PathfinderDsf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    @DisplayName("Проверка поиска пути в глубину")
    void checkFindPathDepth() {
        // given
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(9, 0);
        BacktrackingMaze maze = new BacktrackingMaze(10, 15);
        Cell[][] gridMaze = maze.generate();
        PathfinderDsf pathfinder = new PathfinderDsf(gridMaze, start, end);

        // when
        List<Coordinate> coordinates = pathfinder.getPath();
        Coordinate realEndCoordinate = coordinates.get(coordinates.size() - 1);

        // then
        assertThat(end.row()).isEqualTo(realEndCoordinate.col());
        assertThat(end.col()).isEqualTo(realEndCoordinate.row());
    }
}
