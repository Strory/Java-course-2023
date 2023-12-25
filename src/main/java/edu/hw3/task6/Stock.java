package edu.hw3.task6;

public class Stock implements Comparable<Stock> {
    private String stockName;
    private int price;

    public Stock(String name, int price) {
        this.stockName = name;
        this.price = price;
    }

    public String getStockName() {
        return stockName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Stock otherPrices) {
        return Integer.compare(otherPrices.price, this.price);
    }
}
