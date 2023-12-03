package edu.hw9.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class TreeGenerator {
    private final Map<String, List<FileUnit>> tree = new ConcurrentHashMap<>();
    private final int maxUnits = 1500;
    private final int maxLevel = 6;
    private final int maxSize = 5000;
    Random rand = new Random();

    public void generateTree() {
        String rootName = "root";
        List<FileUnit> branches = new ArrayList<>();
        tree.put(rootName, branches);
        generate(rootName, 1);
    }

    public Map<String, List<FileUnit>> getTree() {
        generateTree();
        return tree;
    }

    @SuppressWarnings("MagicNumber")
    private void generate(String path, int lvl) {
        if (lvl == maxLevel) {
            return;
        }
        int unitsCount = rand.nextInt(maxUnits);
        int branchNumber = 1;
        int fileNumber = 1;
        for (int i = 0; i < unitsCount; ++i) {
            String newPath;
            int size;
            Extension extension;
            FileType type = rand.nextInt(100) == 0 ? FileType.DIRECTORY : FileType.FILE;
            if (type == FileType.DIRECTORY) {
                newPath = "%s/lvl_%d_branch%d".formatted(path, lvl, branchNumber++);
                size = 0;
                extension = null;

                List<FileUnit> branches = new ArrayList<>();
                tree.put(newPath, branches);

                generate(newPath, lvl + 1);
            } else {
                newPath = "%s/file%d".formatted(path, fileNumber++);
                size = rand.nextInt(maxSize);
                extension = rand.nextInt(2) == 0 ? Extension.MD : Extension.TXT;
            }
            FileUnit unit = new FileUnit(newPath, type, size, extension);
            tree.get(path).add(unit);
        }
    }
}
