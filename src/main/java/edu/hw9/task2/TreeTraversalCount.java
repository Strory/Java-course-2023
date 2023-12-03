package edu.hw9.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;

public class TreeTraversalCount extends RecursiveTask {
    private String node;
    private Map<String, List<FileUnit>> tree;
    private final int count;

    public TreeTraversalCount(String node, Map<String, List<FileUnit>> tree, int count) {
        this.node = node;
        this.tree = tree;
        this.count = count;
    }

    @SuppressWarnings("HiddenField")
    @Override
    protected ConcurrentSkipListSet<String> compute() {
        List<TreeTraversalCount> tasks = new ArrayList<>();
        for (FileUnit unit : tree.get(node)) {
            if (unit.type().equals(FileType.DIRECTORY)) {
                TreeTraversalCount task = new TreeTraversalCount(unit.path(), tree, this.count);
                tasks.add(task);
            }
        }
        for (TreeTraversalCount task : tasks) {
            task.fork();
        }

        ConcurrentSkipListSet<String> directories = new ConcurrentSkipListSet<>();
        long count = tree.get(node).stream()
            .filter(unit -> unit.type()
                .equals(FileType.FILE))
            .count();

        if (count > this.count) {
            directories.add(node);
        }

        for (TreeTraversalCount task : tasks) {
            ConcurrentSkipListSet<String> joinSet = (ConcurrentSkipListSet<String>) task.join();
            directories.addAll(joinSet);
        }
        return directories;
    }
}
