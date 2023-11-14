package edu.hw6;

import edu.hw6.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка содержимого созданного файла")
    void checkFileData() {
        //given
        String realAnswer = "";
        String expectAnswer = "Programming is learned by writing programs. ― Brian Kernighan";
        Task4.outputStreamComposition();
        File file = new File("d://test/2.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            realAnswer = stringBuilder.toString();
        } catch (IOException e) {
        }

        //expect
        assertThat(expectAnswer).isEqualTo(realAnswer);
    }
}
