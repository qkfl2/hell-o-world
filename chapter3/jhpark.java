package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(
            Files.list(Paths.get(""))
                    .filter(Chapter3Helper::checkTestFile)
                    .flatMap(Chapter3Helper.throwWrapper(Files::lines))
                    .collect(Collectors.groupingBy(Chapter3Helper::getDomain, Collectors.counting()))
        );

    }

    @FunctionalInterface
    private interface Chapter3Helper<T,R> extends Function<T,R> {
        @Override
        default R apply(T t) {
            try {
                return throwingApply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static<T,R> Function<T,R> throwWrapper(Chapter3Helper<T,R> f) {
            return f;
        }

        R throwingApply(T t) throws Exception;

        static boolean checkTestFile(Path path) {
            return path.toString().matches("[1-3].txt");
        }

        static String getDomain(String email) {
            return email.substring(email.lastIndexOf("@"));
        }
    }
}
