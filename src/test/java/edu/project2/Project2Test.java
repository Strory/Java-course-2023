package edu.project2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
