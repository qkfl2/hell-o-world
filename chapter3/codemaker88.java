package Chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by codemaker88 on 2016-10-29.
 */
public class Chapter3 {
    public static void main(String[] args) {
        try {
            Map<String, Long> result = Files.list(Paths.get("./input/Chapter3"))
                    .flatMap(MethodReference::readData)
                    .collect(Collectors.groupingBy(MethodReference::getDomain, Collectors.counting()));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MethodReference {
        static Stream<String> readData(Path path) {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        static String getDomain(String email) {
            return email.substring(email.indexOf("@") + 1);
        }
    }
}
