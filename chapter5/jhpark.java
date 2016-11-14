package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        try (final BufferedWriter writer = Files.newBufferedWriter(Paths.get("jhpark.txt"))) {
            Fan.streamFromFiles(Files.list(Paths.get("")))
                    .filter(fan -> fan.age >= 20)
                    .filter(fan -> fan.email.contains("girl"))
                    .filter(fan -> fan.isUniqueEmail)
                    .forEach(fan -> fan.sendCongratulation(writer));
        }

    }

    private static final class Fan {
        static final Set<String> sEmails = new HashSet<>();

        private final String name;
        private final String email;
        private final int age;
        private final boolean isUniqueEmail;

        private Fan(String name, String email, String birthday) {
            this.name = name;
            this.email = email;
            this.age = Chapter5Helper.isValidDate(birthday)
                    ? LocalDateTime.now().getYear() - Integer.valueOf(birthday.split("-")[0]) : 0;
            this.isUniqueEmail = sEmails.add(email);
        }

        private static Stream<Fan> streamFromFiles(Stream<Path> pathStream) {
            return pathStream
                    .filter(Chapter5Helper::checkTestFile)
                    .flatMap(Chapter5Helper::fileToLines)
                    .filter(s -> !s.isEmpty())
                    .map(s -> s.split("\\s+"))
                    .map(parsed -> new Fan(parsed[0], parsed[1], parsed[2]));
        }

        private void sendCongratulation(BufferedWriter writer) {
            try {
                writer.write(this.name + "님 안녕하세요 당첨을 축하합니다!\n");
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    private static final class Chapter5Helper {
        private static Stream<String> fileToLines(Path path) {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        private static boolean checkTestFile(Path path) {
            return isValidDate(path.toString().replaceFirst("[.][^.]+$", ""));
        }

        private static boolean isValidDate(String inDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            return dateFormat.parse(inDate, new ParsePosition(0)) != null;
        }
    }
}
