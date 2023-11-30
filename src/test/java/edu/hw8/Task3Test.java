package edu.hw8;

import static org.assertj.core.api.Assertions.assertThat;

import edu.hw8.task3.HashFunctions;
import edu.hw8.task3.Rogue;
import edu.hw8.task3.RogueWithThreads;
import edu.hw8.task3.SomeDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class Task3Test {
    @Test
    @DisplayName("Проверка соответствия пароля и имени")
    void checkCorrectPassName() {
        //given
        Map<String, String> database = SomeDatabase.getDatabase();
        database.put(HashFunctions.getHash("pass"), "John");
        RogueWithThreads rogue = new RogueWithThreads(database);

        //when
        Map<String, String> decryptedBase = rogue.getDatabase();

        //then
        assertThat("John").isEqualTo(decryptedBase.get("pass"));
    }
}
