package edu.hw9.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class TreeTraversalPredicate extends RecursiveTask {
    private String node;
    private Map<String, List<FileUnit>> tree;
    private final int size;
    private final Extension extension;

    public TreeTraversalPredicate(String node, Map<String, List<FileUnit>> tree, int size, Extension extension) {
        this.node = node;
        this.tree = tree;
        this.size = size;
        this.extension = extension;
    }

    @Override
    protected ConcurrentSkipListSet<String> compute() {
        List<TreeTraversalPredicate> tasks = new ArrayList<>();
        for (FileUnit unit : tree.get(node)) {
            if (unit.type().equals(FileType.DIRECTORY)) {
                TreeTraversalPredicate task = new TreeTraversalPredicate(unit.path(), tree, size, extension);
                tasks.add(task);
            }
        }
        for (TreeTraversalPredicate task : tasks) {
            task.fork();
        }

        ConcurrentSkipListSet<String> files = new ConcurrentSkipListSet<>(
            tree.get(node).stream()
                .filter(unit -> unit.type()
                    .equals(FileType.FILE) && unit.extension().equals(extension) && unit.size() > size)
                .map(FileUnit::path)
                .collect(Collectors.toSet()));

        for (TreeTraversalPredicate task : tasks) {
            ConcurrentSkipListSet<String> joinSet = (ConcurrentSkipListSet<String>) task.join();
            files.addAll(joinSet);
        }

        return files;
    }
}
