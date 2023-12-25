package edu.hw9;

import edu.hw9.task2.Crawler;
import edu.hw9.task2.Extension;
import edu.hw9.task2.FileUnit;
import edu.hw9.task2.MyTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    @DisplayName("Проверка правильного поиска директории с более чем 3-мя файлами")
    void checkCorrectSearchCount() {
        // given
        MyTree treeGenerator = new MyTree();
        Map<String, List<FileUnit>> tree = treeGenerator.getTree();
        Crawler crawler = new Crawler(tree);

        // when
        Set<String> resultCount = crawler.getAroundCount(3);

        // then
        assertTrue(resultCount.contains("root/lvl_1_branch1"));
        assertThat(1).isEqualTo(resultCount.size());
    }

    @Test
    @DisplayName("Проверка правильного поиска файлов по предикату")
    void checkCorrectSearchPredicate() {
        // given
        MyTree treeGenerator = new MyTree();
        Map<String, List<FileUnit>> tree = treeGenerator.getTree();
        Crawler crawler = new Crawler(tree);

        // when
        Set<String> resultPredicate = crawler.getAroundPredicate(3, Extension.TXT);

        // then
        assertThat(3).isEqualTo(resultPredicate.size());
    }
}
