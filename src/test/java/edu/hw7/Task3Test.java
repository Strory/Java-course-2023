package edu.hw7;

import edu.hw7.task3.PeopleAdder;
import edu.hw7.task3.Person;
import edu.hw7.task3.SomeDatabase;
import edu.hw7.task3_5.SomeDatabase2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Проверка: Сначала нельзя найти, потому что имя null, потом можно найти")
    void checkCorrectFind() {
        //given
        SomeDatabase database = new SomeDatabase();
        List<Person> dataSet = generateData(20);
        Thread adder1 = new PeopleAdder(database, dataSet, 0, 10);

        //expect
        Thread finder1 = new Thread(() -> {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Person> persons = database.findByAddress("address6");
            assertThat(0).isEqualTo(persons.size());
        });
        Thread changer = new Thread(() -> {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            database.change(new Person(6, "name6", "address6", "4324"));
        });
        Thread finder2 = new Thread(() -> {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Person> persons = database.findByAddress("address6");
            assertThat(1).isEqualTo(persons.size());
        });
        adder1.start();
        finder1.start();
        changer.start();
        finder2.start();

        try {
            finder1.join();
            changer.join();
            finder2.join();

            adder1.join();
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Проверка: Сначала нельзя найти, потому что имя null, потом можно найти. ReadWrite")
    void checkCorrectFindWithReadWriteLock() {
        //given
        SomeDatabase2 database = new SomeDatabase2();
        List<Person> dataSet = generateData(20);
        Thread adder1 = new PeopleAdder(database, dataSet, 0, 10);

        //expect
        Thread finder1 = new Thread(() -> {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Person> persons = database.findByAddress("address6");
            assertThat(0).isEqualTo(persons.size());
        });
        Thread changer = new Thread(() -> {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            database.change(new Person(6, "name6", "address6", "4324"));
        });
        Thread finder2 = new Thread(() -> {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Person> persons = database.findByAddress("address6");
            assertThat(1).isEqualTo(persons.size());
        });
        adder1.start();
        finder1.start();
        changer.start();
        finder2.start();

        try {
            finder1.join();
            changer.join();
            finder2.join();

            adder1.join();
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }

    public static List<Person> generateData(int count) {
        List<Person> dataSet = new ArrayList<>();
        ++count;
        for (int i = 1; i < count; ++i) {
            int id = i;
            String name = "name%d".formatted(i % 40);
            String address = "address%d".formatted(i % 10);
            String phone = "899977754%d".formatted(i);
            if (i%6 == 0) {
                name = null;
            } else if (i % 10 == 0) {
                address = null;
            } else if (i % 9 == 0) {
                phone = null;
            }
            dataSet.add(new Person(id, name, address, phone));
        }
        return dataSet;
    }
}
