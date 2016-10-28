package com.company;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(
            Files.list(Paths.get(""))
                    .filter(Chapter3Helper::checkTestFile)
                    .flatMap(Chapter3Helper::byLines)
                    .collect(Collectors.groupingBy(Chapter3Helper::getDomain, Collectors.counting()))
        );

    }

    private interface Chapter3Helper {
        static Stream<String> byLines(Path path) {
            try { 
                return Files.lines(path); 
            } catch (IOException e) { throw new UncheckedIOException(e); }
        }

        static boolean checkTestFile(Path path) {
            return path.toString().matches("[1-3].txt");
        }

        static String getDomain(String email) {
            return email.substring(email.lastIndexOf("@"));
        }
    }
}
