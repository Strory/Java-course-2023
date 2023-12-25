package edu.hw6;

import edu.hw6.task1.DiskMap;
import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Проверка названия новости по id")
    void checkTitle() {
        //given
        String expectAnswer = "Building an occupancy sensor with a $5 ESP32 and a serverless DB";
        long id = 38252566;

        //when
        String realAnswer = HackerNews.news(38252566);

        //then
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }
}
