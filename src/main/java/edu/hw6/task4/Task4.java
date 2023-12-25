package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {}

    public static void outputStreamComposition() {
        String text = "Programming is learned by writing programs. ― Brian Kernighan";
        Path path = Path.of("d://test/2.txt");

        try (OutputStream outputStream = new CheckedOutputStream(
            new BufferedOutputStream(
                Files.newOutputStream(path)
            ),
            new Adler32()
        );
             PrintWriter printWriter = new PrintWriter(
                 new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
             )) {
            printWriter.println(text);
        } catch (IOException e) {
            return;
        }
    }
}
