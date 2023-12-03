package edu.hw9.task2;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class Console {
    private static Logger logger = Logger.getLogger("Console");

    private Console() {}

    @SuppressWarnings("MagicNumber")
    public static void showResult() {
        TreeGenerator treeGenerator = new TreeGenerator();
        Map<String, List<FileUnit>> tree = treeGenerator.getTree();
        Crawler crawler = new Crawler(tree);

        Set<String> resultCount = crawler.getAroundCount(4);
        Set<String> resultPredicate = crawler.getAroundPredicate(3, Extension.TXT);

        logger.info("Данные по узлам, где больше 4 файлов");
        for (String node : resultCount) {
            logger.info(node);
        }

        logger.info("Данные по файлам, где размер больше 3 и расширение TXT");
        for (String node : resultPredicate) {
            logger.info(node);
        }
    }
}
