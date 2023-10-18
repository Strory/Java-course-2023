package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test()
    @DisplayName("Проверка успешного соединения через DefaultConnectionManager")
    void checkSuccessfulDefaultConnect() {
        //given
        ConnectionManager manager = new DefaultConnectionManager();
        int maxAttempts = 3;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        try {
            executor.updatePackages();
            // Строка успешного завершения
        } catch (ConnectionException e) {
            Assertions.fail();
        }
    }

    @Test()
    @DisplayName("Проверка неудачного соединения через DefaultConnectionManager")
    void checkUnsuccessfulDefaultConnect() {
        //given
        ConnectionManager manager = new DefaultConnectionManager();
        int maxAttempts = 2;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        try {
            executor.updatePackages();
            Assertions.fail();
        } catch (ConnectionException e) {
            assertThat(e.getMessage()).isEqualTo("Превышено количество попыток");
        }
    }

    @Test()
    @DisplayName("Проверка успешного соединения через FaultyConnectionManager")
    void checkSuccessfulFaultyConnect() {
        //given
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 5;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        try {
            executor.updatePackages();
            // Строка успешного завершения
        } catch (ConnectionException e) {
            Assertions.fail();
        }
    }

    @Test()
    @DisplayName("Проверка неудачного соединения через FaultyConnectionManager")
    void checkUnsuccessfulFaultyConnect() {
        //given
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 2;

        //when
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        //then
        try {
            executor.updatePackages();
            Assertions.fail();
        } catch (ConnectionException e) {
            assertThat(e.getMessage()).isEqualTo("Превышено количество попыток");
        }
    }
}

