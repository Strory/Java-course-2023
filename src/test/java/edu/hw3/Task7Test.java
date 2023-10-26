package edu.hw3;

import edu.hw3.task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка компаратора")
    void checkComparator() {
        //given
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator());

        //when
        tree.put(null, "it is null!!!");

        //then
        assertThat(tree.containsKey(null)).isTrue();
    }
}
