package edu.hw6.task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return AbstractFilter.this.accept(entry) && other.accept(entry);
            }
        };
    }

    static AbstractFilter readable(boolean readable) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isReadable(entry) == readable;
            }
        };
    }

    static AbstractFilter writable(boolean writable) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isWritable(entry) == writable;
            }
        };
    }

    static AbstractFilter size(int size) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.size(entry) > size;
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return entry.getFileName().toString().matches(glob);
            }
        };
    }

    static AbstractFilter regexMatches(String regex) {
        return new AbstractFilter() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return entry.getFileName().toString().matches(regex);
            }
        };
    }

    static AbstractFilter magicNumber(byte... magicIdentity) {
        return new AbstractFilter() {

            @Override
            public boolean accept(Path entry) {
                try (InputStream is = new FileInputStream(entry.toFile())) {
                    byte[] header = new byte[magicIdentity.length];
                    if (is.read(header) != magicIdentity.length) {
                        return false;
                    }
                    return Arrays.equals(header, magicIdentity);
                } catch (IOException e) {
                    return false;
                }
            }
        };
    }
}
