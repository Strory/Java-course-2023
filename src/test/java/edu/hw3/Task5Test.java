package edu.hw3;

import edu.hw3.task4.Task4;
import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Проверка простых имен")
    void checkSimpleNames() {
        //given
        String[] arrNames = {"John Locke",
            "Thomas Aquinas", "David Hume", "Rene Descartes"};
        Task5 parseContact = new Task5();
        Contact[] expectAnswer = {new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")};

        //when
        Contact[] realAnswer = parseContact.parseContacts(arrNames, "ASC");

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка имен без фамилий")
    void checkNamesWithoutLastNames() {
        //given
        String[] arrNames = {"John Locke",
            "Thomas Aquinas", "David Hume", "Rene Descartes", "Emmanuil", "Isaac"};
        Task5 parseContact = new Task5();
        Contact[] expectAnswer = {
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("Emmanuil", null),
            new Contact("David", "Hume"),
            new Contact("Isaac", null),
            new Contact("John", "Locke")};

        //when
        Contact[] realAnswer = parseContact.parseContacts(arrNames, "ASC");

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }

    @Test
    @DisplayName("Проверка сортировки по убыванию")
    void checkDescSort() {
        //given
        String[] arrNames = {"John Locke",
            "Thomas Aquinas", "David Hume", "Rene Descartes", "Emmanuil", "Isaac"};
        Task5 parseContact = new Task5();
        Contact[] expectAnswer = {
            new Contact("John", "Locke"),
            new Contact("Isaac", null),
            new Contact("David", "Hume"),
            new Contact("Emmanuil", null),
            new Contact("Rene", "Descartes"),
            new Contact("Thomas", "Aquinas"),
        };

        //when
        Contact[] realAnswer = parseContact.parseContacts(arrNames, "DESC");

        //then
        assertThat(realAnswer).isEqualTo(expectAnswer);
    }
}
