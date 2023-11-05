package edu.project2;

import edu.project2.pathfinding.Coordinate;
import edu.project2.pathfinding.Pathfinder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project2Test {
    @Test
    @DisplayName("Проверка на изолированную область")
    void checkIsolatedArea() {
        // given
        // Ожидание: все ячейки принадлежат одному множеству

        // when
        EllersMaze maze = new EllersMaze(100, 100);
        Cell[][] realGrid = maze.generate();

        // then
        int firstSetId = realGrid[0][0].getId();
        for (Cell[] line : realGrid) {
            for (Cell cell : line) {
                if (cell.getId() != firstSetId) {
                    Assertions.fail("Изолированная область!!!");
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка поиска пути")
    void checkFindPath() {
        // given
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(9, 0);
        EllersMaze maze = new EllersMaze(10, 15);
        Cell[][] gridMaze = maze.generate();

        // when
        Pathfinder pathfinder = new Pathfinder(gridMaze, start, end);
        List<Coordinate> coordinates = pathfinder.getCoordinateList();
        Coordinate realEndCoordinate = coordinates.get(coordinates.size() - 1);

        // then
        assertThat(end.row()).isEqualTo(realEndCoordinate.row());
        assertThat(end.col()).isEqualTo(realEndCoordinate.col());
    }
}
