package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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

    private static class Fan {
        static final Set<String> sEmails = new HashSet<>();
        final String name;
        final String email;
        final int age;
        final boolean isUniqueEmail;

        static Stream<Fan> streamFromFiles(Stream<Path> pathStream) {
            return pathStream
                    .filter(Chapter5Helper::checkTestFile)
                    .flatMap(Chapter5Helper::fileToLines)
                    .filter(s -> !s.isEmpty())
                    .map(Fan::parse);
        }

        static Fan parse(String data) {
            final String[] parsed = data.split("\\s+");
            return new Fan(parsed[0], parsed[1], parsed[2]);
        }

        Fan(String name, String email, String birthday) {
            this.name = name;
            this.email = email;
            this.age = Chapter5Helper.isValidDate(birthday) ? calcAge(birthday) : 0;
            this.isUniqueEmail = sEmails.add(email);
        }

        void sendCongratulation(BufferedWriter writer) {
            try {
                writer.write(this.name + "님 안녕하세요 당첨을 축하합니다!\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int calcAge(String birthday) {
            return LocalDateTime.now().getYear() - Integer.valueOf(birthday.split("-")[0]);
        }
    }

    interface Chapter5Helper {

        static Stream<String> fileToLines(Path path) {
            try {
                return Files.lines(path);
            } catch (IOException e) { throw new UncheckedIOException(e); }
        }

        static boolean checkTestFile(Path path) {
            return isValidDate(path.toString().replaceFirst("[.][^.]+$", ""));
        }

        static boolean isValidDate(String inDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);

            try {
                dateFormat.parse(inDate.trim());
            } catch (ParseException pe) {
                return false;
            }

            return true;
        }
    }

}
