package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> elements;
    private int currentIndex;

    public BackwardIterator(List<T> elements) {
        this.elements = elements;
        this.currentIndex = elements.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T element = elements.get(currentIndex);
            --currentIndex;
            return element;
        }
        throw new NoSuchElementException();
    }
}
