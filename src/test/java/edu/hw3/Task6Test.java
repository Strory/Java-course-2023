package edu.hw3;

import edu.hw3.task4.Task4;
import edu.hw3.task6.SomeMarcket;
import edu.hw3.task6.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Проверка нахождения самой дорогой акции")
    void checkFindMostExpensiveStock() {
        //given
        SomeMarcket myMarcket = new SomeMarcket();
        Stock stock1 = new Stock("Stock1", 100);
        Stock stock2 = new Stock("Stock2", 900);
        Stock stock3 = new Stock("Stock3", 300);
        Stock stock4 = new Stock("Stock4", 500);
        myMarcket.add(stock1);
        myMarcket.add(stock2);
        myMarcket.add(stock3);
        myMarcket.add(stock4);

        //when
        Stock realAnswer = myMarcket.mostValuableStock();

        //then
        assertThat(realAnswer).isEqualTo(stock2);
    }

    @Test
    @DisplayName("Проверка нахождения самой дорогой акции после удаления")
    void checkFindMostExpensiveStockAfterRemoval() {
        //given
        SomeMarcket myMarcket = new SomeMarcket();
        Stock stock1 = new Stock("Stock1", 100);
        Stock stock2 = new Stock("Stock2", 900);
        Stock stock3 = new Stock("Stock3", 300);
        Stock stock4 = new Stock("Stock4", 500);
        myMarcket.add(stock1);
        myMarcket.add(stock2);
        myMarcket.add(stock3);
        myMarcket.add(stock4);
        myMarcket.remove(stock2);

        //when
        Stock realAnswer = myMarcket.mostValuableStock();

        //then
        assertThat(realAnswer).isEqualTo(stock4);
    }
}
