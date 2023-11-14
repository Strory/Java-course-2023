package edu.hw6;

import static org.assertj.core.api.Assertions.assertThat;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class Task1Test {
    @Test
    @DisplayName("Проверка метода get")
    void checkGetMethod() {
        //given
        String key1 = "key1";
        String key2 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        Map<String, String> disk = new DiskMap("file1.txt");
        disk.put(key1, value1);
        disk.put(key2, value2);
        String expectAnswer = value1;

        //when
        String realAnswer = disk.get(key1);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }

    @Test
    @DisplayName("Проверка метода size")
    void checkSizeMethod() {
        //given
        String key1 = "key1";
        String key2 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        Map<String, String> disk = new DiskMap("file2.txt");
        disk.put(key1, value1);
        disk.put(key2, value2);
        int expectAnswer = 2;

        //when
        int realAnswer = disk.size();

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }
}
