package chapter5;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by bross on 2016. 11. 15..
 */
public class Chapter5 {
    public static void main(String[] args) {
        Set<Person> personSet = new HashSet<>();
        try {
            Files.list(Paths.get("/Users/bross/Documents/Coding/Java/Java8/Practice/src/chapter5/files"))
                    .forEach(file -> {
                        try (Stream<String> stream = Files.lines(Paths.get(file.toString()))) {
                            stream.forEach(line -> {
                                if (!line.equals("")) {
                                    personSet.add(new Person(line));
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        personSet.stream().filter(Person::isIDCotainGirl).filter(Person::is20Over).forEach(Person::saveFile);
    }

    private static class Person extends Object {
        int age;
        String name;
        String email;

        Person(String line) {
            String[] tokens = line.split(" ");
            name = tokens[0];
            email = tokens[1];
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthDay = transFormat.parse(tokens[2]);
                Calendar today = Calendar.getInstance();
                Calendar.getInstance().setTime(birthDay);
                Calendar birthDayCalendar = Calendar.getInstance();
                birthDayCalendar.setTime(birthDay);
                age = today.get(Calendar.YEAR) - birthDayCalendar.get(Calendar.YEAR);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        boolean is20Over() {
            return age >= 20;
        }

        String getId() {
            return email.substring(0, email.lastIndexOf("@"));
        }

        boolean isIDCotainGirl() {
            return getId().contains("girl");
        }

        @Override
        public boolean equals(Object person) {
            return email.equals(((Person) person).email);
        }

        void saveFile() {
            String directory = "/Users/bross/Documents/Coding/Java/Java8/Practice/src/chapter5/save/";
            String message = name + "님 안녕하세요 당첨을 축하합니다!";
            String fileName = getId() + ".txt";
            try {
                FileWriterEAM.use(directory + fileName, writerEAM -> writerEAM.writeStuff(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FunctionalInterface
    interface UseInterface<T, X extends Throwable> {
        void accept(T instance) throws X;
    }

    private static class FileWriterEAM implements AutoCloseable {

        private final FileWriter writer;

        FileWriterEAM(final String fileName) throws IOException {
            writer = new FileWriter(fileName);
        }

        void writeStuff(final String message) throws IOException {
            writer.write(message);
        }

        @Override
        public void close() throws IOException {
            writer.close();
        }

        static void use(final String fileName, final UseInterface<FileWriterEAM, IOException> block) throws IOException {
            FileWriterEAM writerEAM = new FileWriterEAM(fileName);
            try {
                block.accept(writerEAM);
            } finally {
                writerEAM.close();
            }

        }
    }
}
