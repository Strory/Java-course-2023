package edu.hw3.task6;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SomeMarcket implements StockMarcket {
    private ArrayList<Stock> stockList = new ArrayList<>(); // подписать, что для данной задачи не нужен
    private PriorityQueue<Stock> stockQueue = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        stockList.add(stock);
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockList.remove(stock);
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}
