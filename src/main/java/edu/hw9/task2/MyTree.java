package edu.hw9.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyTree {
    private final Map<String, List<FileUnit>> tree = new ConcurrentHashMap<>();

    @SuppressWarnings({"MagicNumber", "LocalVariableName"})
    public Map<String, List<FileUnit>> getTree() {
        List<FileUnit> lvl1 = new ArrayList<>();
        for (int i = 1; i < 4; ++i) {
            lvl1.add(new FileUnit("root/lvl_1_branch" + i, FileType.DIRECTORY, 0, null));
        }
        tree.put("root", lvl1);

        List<FileUnit> lvl2_1 = new ArrayList<>();
        for (int i = 1; i < 6; ++i) {
            lvl2_1.add(new FileUnit("root/lvl_1_branch1/file" + i, FileType.FILE, 3, Extension.TXT));
        }
        tree.put("root/lvl_1_branch1", lvl2_1);

        List<FileUnit> lvl2_2 = new ArrayList<>();
        for (int i = 1; i < 4; ++i) {
            lvl2_2.add(new FileUnit("root/lvl_1_branch2/file" + i, FileType.FILE, 4, Extension.TXT));
        }
        tree.put("root/lvl_1_branch2", lvl2_2);

        List<FileUnit> lvl2_3 = new ArrayList<>();
        tree.put("root/lvl_1_branch3", lvl2_3);

        return tree;
    }
}
