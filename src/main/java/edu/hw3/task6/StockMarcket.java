package edu.hw3.task6;

public interface StockMarcket {
    void add(Stock stock);

    void remove(Stock stock);

    Stock mostValuableStock();
}
