package edu.hw9.task2;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ForkJoinPool;

public class Crawler {
    Map<String, List<FileUnit>> tree;

    public Crawler(Map<String, List<FileUnit>> tree) {
        this.tree = tree;
    }

    @SuppressWarnings("MultipleStringLiterals")
    public Set<String> getAroundCount(int filesCount) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        TreeTraversalCount task = new TreeTraversalCount("root", tree, filesCount);

        Set<String> result = (ConcurrentSkipListSet<String>) forkJoinPool.invoke(task);
        return result;
    }

    @SuppressWarnings("MultipleStringLiterals")
    public Set<String> getAroundPredicate(int fileSize, Extension extension) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        TreeTraversalPredicate task = new TreeTraversalPredicate("root", tree, fileSize, extension);

        Set<String> result = (ConcurrentSkipListSet<String>) forkJoinPool.invoke(task);
        return result;
    }
}
